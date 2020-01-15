package org.usecase.dao

import org.usecase.model.collection.ListFilter
import org.usecase.model.resource.Endereco
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query

/**
 * Data Access Object of Endereco from table endereco
 * @author Simpli CLI generator
 */
class EnderecoDao(val con: AbstractConnector) {

    fun getOne(idEnderecoPk: Long): Endereco? {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("endereco")
                .whereEq("idEnderecoPk", idEnderecoPk)

        return con.getOne(query) {
            Endereco(it)
        }
    }

    fun getList(filter: ListFilter): MutableList<Endereco> {
        // TODO: review generated method
        val query = Query()
                .selectAll()
                .from("endereco")
                .applyListFilter(filter)

        Endereco.orderMap[filter.orderBy]?.also {
            query.orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            query.limit(index, it)
        }

        return con.getList(query) {
            Endereco(it)
        }
    }

    fun count(filter: ListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idEnderecoPk")
                .from("endereco")
                .applyListFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(endereco: Endereco): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("endereco")
                .updateSet(endereco.updateSet())
                .whereEq("idEnderecoPk", endereco.id)

        return con.execute(query).affectedRows
    }

    fun insert(endereco: Endereco): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("endereco")
                .insertValues(endereco.insertValues())

        return con.execute(query).key
    }

    fun exist(idEnderecoPk: Long): Boolean {
        // TODO: review generated method
        val query = Query()
                .select("idEnderecoPk")
                .from("endereco")
                .whereEq("idEnderecoPk", idEnderecoPk)

        return con.exist(query)
    }


    private fun Query.applyListFilter(filter: ListFilter): Query {

        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSome {
                    whereLike("endereco.idEnderecoPk", "%$it%")
                    whereLike("endereco.cep", "%$it%")
                    whereLike("endereco.zipcode", "%$it%")
                    whereLike("endereco.rua", "%$it%")
                    whereLike("endereco.cidade", "%$it%")
                    whereLike("endereco.uf", "%$it%")
                }
            }
        }

        return this
    }

}
