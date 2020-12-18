package org.usecase.app

import br.com.simpli.tools.ResourceLoader

/**
 * Manages email HTML components and its layout based on file name
 * The components must be in src/main/resources/mail-templates/components
 *
 * @author Simpli CLI generator
 */
class MailComponent(val filename: String, val dir: String = "components") {
    /**
     * Creates an HTML string based on a HTML component.
     * You may set the props provided by component.
     *
     * Example:
     *
     * src/main/resources/mail-templates/components/Tag.html
     * ```html
     * <div class="tag">${content}<div>
     * ```
     *
     * ```kotlin
     * val html = MailComponent("Tag.html").build("content" to "Hello World")
     * ```
     *
     * Result:
     *
     * html = ```html
     * <div class="tag">Hello World<div>
     * ```
     */
    fun build(vararg value: Pair<String, Any?>): String = build(value.toMap())
    fun build(value: Map<String, Any?>): String = ResourceLoader.getString("mail-templates/$dir/$filename")
            ?.replace(Regex("""\$\{(\S+)}""", RegexOption.MULTILINE)) {
                val key = it.groups[1]?.value
                value[key]?.toString() ?: ""
            } ?: ""

    companion object {
        /**
         * Build an empty column from "src/main/resources/mail-templates/components/Column.html" component
         * You may provide its weight (12 = width 100%, 6 = width 50%, etc.) or margin
         *
         * Example:
         *
         * ```kotlin
         * val html = MailComponent.buildEmptyColumn(6, 10)
         * ```
         *
         * Result:
         *
         * ```html
         * <column width="50%" margin="10px"></column>
         * ```
         */
        fun buildEmptyColumn(weight: Int? = null, margin: Int? = null): String = buildColumn("", weight, margin)

        /**
         * Build a column from "src/main/resources/mail-templates/components/Column.html" component
         * You may provide its weight (12 = width 100%, 6 = width 50%, etc.) or margin
         *
         * Example:
         *
         * ```kotlin
         * val html = MailComponent.buildColumn("Hello World", 6, 10)
         * ```
         *
         * Result:
         *
         * ```html
         * <column width="50%" margin="10px">Hello World</column>
         * ```
         */
        fun buildColumn(content: String, weight: Int? = null, margin: Int? = null): String {
            val validWeights = when(weight) {
                1 -> 1; 2 -> 2; 3 -> 3; 4 -> 4; 5 -> 5; 6 -> 6; 7 -> 7; 8 -> 8; 9 -> 9; 10 -> 10; 11 -> 11; 12 -> 12
                else -> 12
            }

            val width = (validWeights * 100) / 12

            return MailComponent("Column.html", "layouts").build("width" to "$width%", "margin" to "${margin ?: 0}", "content" to content)
        }

        /**
         * Build a row from "src/main/resources/mail-templates/components/Row.html" component
         * You may provide its margin
         * Its content can be either String or List<String>. Usually the content is a column or a list of columns.
         *
         * Example:
         *
         * ```kotlin
         * val columns = listOf(MailComponent.buildColumn("Foo"), MailComponent.buildColumn("Bar"))
         * val html = MailComponent.buildRow(columns, 10)
         * ```
         *
         * Result:
         * ```html
         * <row>
         *   <column>Foo</column>
         *   <column>Bar</column>
         * </row>
         * ```
         */
        fun buildRow(content: List<String>, margin: Int? = null): String = buildRow(content.joinToString("\n\n"), margin)
        fun buildRow(content: String, margin: Int? = null): String {
            return MailComponent("Row.html", "layouts").build("margin" to "${margin ?: 0}", "content" to content)
        }

        /**
         * Build a grid from "src/main/resources/mail-templates/components/Grid.html" component
         * You may provide the number of columns of each row and the gutter
         * Its content should be a list of anything
         * You can transform each cell based on items from the list
         *
         * Example:
         *
         * src/main/resources/mail-templates/components/Tag.html
         * ```html
         * <div class="tag">${content}<div>
         * ```
         *
         * ```kotlin
         * val list = listOf("Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6")
         * val html = MailComponent.buildGrid(list, 3, 5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         *
         * Result:
         *
         * ```html
         * <grid gutter="5">
         *   <row>
         *     <column>
         *       <div class="tag">Tag1<div>
         *     </column>
         *     <column>
         *       <div class="tag">Tag2<div>
         *     </column>
         *     <column>
         *       <div class="tag">Tag3<div>
         *     </column>
         *   </row>
         *   <row>
         *     <column>
         *       <div class="tag">Tag4<div>
         *     </column>
         *     <column>
         *       <div class="tag">Tag5<div>
         *     </column>
         *     <column>
         *       <div class="tag">Tag6<div>
         *     </column>
         *   </row>
         * </grid>
         * ```
         */
        fun <T> buildGrid(
                content: List<T?>,
                columns: Int,
                gutter: Int? = null,
                transform: (T) -> String
        ): String {
            val weight = when (columns) {
                1 -> 12
                2 -> 6
                3 -> 4
                4 -> 3
                6 -> 2
                12 -> 1
                else -> 12
            }

            return content.asSequence()
                    // removes any blank item
                    .filter { (it as? String)?.isNotBlank() ?: true }
                    // Inserts each item inside of column and replace null to empty columns
                    .map { it?.run { buildColumn(transform(this), weight, gutter) } ?: buildEmptyColumn(weight) }
                    // Chunks into groups of X items
                    .chunked(columns)
                    // Adds empty columns to fill the last row in X items
                    .map { it.toMutableList().apply { while(size < columns) add(buildEmptyColumn(weight)) } }
                    // Inserts each group of columns inside of row
                    .map { buildRow(it) }
                    // Joins all the rows
                    .joinToString ("\n\n\n")
                    // Transforms into grid
                    .run {
                        MailComponent("Grid.html", "layouts").build("margin" to gutter?.run { -this }, "content" to this)
                    }
        }

        /**
         * Build a grid from "src/main/resources/mail-templates/components/Grid.html" component
         * You may provide the gutter and a list of pairs of items with its weight
         * You can transform each cell based on items from the list
         *
         * Example:
         *
         * src/main/resources/mail-templates/components/Tag.html
         * ```html
         * <div class="tag">${content}<div>
         * ```
         *
         * ```kotlin
         * val list = listOf(Pair("Tag1", 4), Pair("Tag2", 2), Pair("Tag3", 6), Pair("Tag4", 3))
         * val html = MailComponent.buildGrid(list, 5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         *
         * Result:
         *
         * ```html
         * <grid gutter="5">
         *   <row>
         *     <column weight="4">
         *       <div class="tag">Tag1<div>
         *     </column>
         *     <column weight="2">
         *       <div class="tag">Tag2<div>
         *     </column>
         *     <column weight="6">
         *       <div class="tag">Tag3<div>
         *     </column>
         *   </row>
         *   <row>
         *     <column weight="3">
         *       <div class="tag">Tag4<div>
         *     </column>
         *   </row>
         * </grid>
         * ```
         */
        fun <T> buildGridByPairs(
                content: List<Pair<T?, Int>>,
                gutter: Int? = null,
                transform: (T) -> String
        ) : String {
            val rows = mutableListOf<List<String>>().apply {
                val maxWeight = 12
                var pivotWeight = 0
                var columnsCache = mutableListOf<String>()

                val iterator = content.asSequence()
                        // removes any blank item
                        .filter { (it.first as? String)?.isNotBlank() ?: true }
                        // Inserts each item inside of column and replace null to empty columns
                        .map {
                            Pair(
                                    it.first?.run { buildColumn(transform(this), it.second, gutter) } ?: buildEmptyColumn(it.second),
                                    it.second
                            )
                        }
                        .iterator()

                // Distributes columns in rows
                while (iterator.hasNext()) {
                    val item = iterator.next()

                    pivotWeight += item.second

                    var emptyWeight = maxWeight - pivotWeight

                    // If next column can fill the current row
                    if (pivotWeight <= maxWeight) {
                        columnsCache.add(item.first)
                    } else {
                        pivotWeight = item.second
                        emptyWeight = maxWeight - pivotWeight

                        add(columnsCache)
                        columnsCache = mutableListOf(item.first)
                    }

                    // If it is the last one
                    if (!iterator.hasNext()) {
                        if (emptyWeight != 0) {
                            columnsCache.add(buildEmptyColumn(emptyWeight))
                        }
                        add(columnsCache)
                    }
                }
            }

            return rows.asSequence()
                    // Inserts each group of columns inside of row
                    .map { buildRow(it) }
                    // Joins all the rows
                    .joinToString ("\n\n\n")
                    // Transforms into grid
                    .run {
                        MailComponent("Grid.html", "layouts").build("margin" to gutter?.run { -this }, "content" to this)
                    }
        }

        /**
         * Extension function of MailComponent.buildColumn
         *
         * Example of usage:
         *
         * ```kotlin
         * "Foo".buildHtmlColumn(6, 10)
         * ```
         *
         * The same of:
         *
         * ```kotlin
         * MailComponent.buildHtmlColumn("Foo", 6, 10)
         * ```
         */
        fun String.buildHtmlColumn(weight: Int? = null, margin: Int? = null): String = buildColumn(this, weight, margin)

        /**
         * Extension function of MailComponent.buildRow
         *
         * Example of usage:
         *
         * ```kotlin
         * "Foo".buildHtmlColumn(6, 10).buildHtmlRow(20)
         * // or
         * listOf("Foo", "Bar").buildHtmlColumn(6, 10).buildHtmlRow(20)
         * ```
         *
         * The same of:
         *
         * ```kotlin
         * MailComponent.buildRow(MailComponent.buildHtmlColumn("Foo", 6, 10), 20)
         * // or
         * MailComponent.buildRow(MailComponent.buildHtmlColumn(listOf("Foo", "Bar"), 6, 10), 20)
         * ```
         */
        fun String.buildHtmlRow(margin: Int? = null): String = buildRow(this, margin)
        fun List<String>?.buildHtmlRow(margin: Int? = null): String = buildRow(this ?: emptyList(), margin)

        /**
         * Extension function of MailComponent.buildGrid
         *
         * Example of usage:
         *
         * ```kotlin
         * val list = listOf("Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6")
         * list.buildHtmlGrid(3, 5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         *
         * The same of:
         *
         * ```kotlin
         * val list = listOf("Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6")
         * MailComponent.buildGrid(list, 3, 5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         */
        fun List<String>?.buildHtmlGrid(columns: Int, gutter: Int? = null): String = buildHtmlGrid(columns, gutter) { it }
        fun <T> List<T>?.buildHtmlGrid(columns: Int, gutter: Int? = null, transform: (T) -> String): String {
            return buildGrid(this ?: emptyList(), columns, gutter, transform)
        }

        /**
         * Extension function of MailComponent.buildGrid
         *
         * Example of usage:
         *
         * ```kotlin
         * val list = listOf(Pair("Tag1", 4), Pair("Tag2", 2), Pair("Tag3", 6), Pair("Tag4", 3))
         * list.buildHtmlGrid(5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         *
         * The same of:
         *
         * ```kotlin
         * val list = listOf(Pair("Tag1", 4), Pair("Tag2", 2), Pair("Tag3", 6), Pair("Tag4", 3))
         * MailComponent.buildGrid(list, 5) { MailComponent("Tag.html").build("content" to it) }
         * ```
         */
        fun List<Pair<String?, Int>>?.buildHtmlGridByPairs(gutter: Int? = null) = buildHtmlGridByPairs(gutter) { it }
        fun <T> List<Pair<T?, Int>>?.buildHtmlGridByPairs(gutter: Int? = null, transform: (T) -> String): String {
            return buildGridByPairs(this ?: emptyList(), gutter, transform)
        }
    }
}
