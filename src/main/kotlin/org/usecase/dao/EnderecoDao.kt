package org.usecase.dao

import org.usecase.model.filter.EnderecoListFilter
import org.usecase.model.resource.Endereco
import org.usecase.model.rm.EnderecoRM
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
                .selectEndereco()
                .from("endereco")
                .whereEq("idEnderecoPk", idEnderecoPk)

        return con.getOne(query) {
            EnderecoRM.build(it)
        }
    }

    fun getList(filter: EnderecoListFilter): MutableList<Endereco> {
        // TODO: review generated method
        val query = Query()
                .selectEndereco()
                .from("endereco")
                .whereEnderecoFilter(filter)
                .orderAndLimitEndereco(filter)

        return con.getList(query) {
            EnderecoRM.build(it)
        }
    }

    fun count(filter: EnderecoListFilter): Int {
        // TODO: review generated method
        val query = Query()
                .countRaw("DISTINCT idEnderecoPk")
                .from("endereco")
                .whereEnderecoFilter(filter)

        return con.getFirstInt(query) ?: 0
    }

    fun update(endereco: Endereco): Int {
        // TODO: review generated method
        val query = Query()
                .updateTable("endereco")
                .updateEnderecoSet(endereco)
                .whereEq("idEnderecoPk", endereco.id)

        return con.execute(query).affectedRows
    }

    fun insert(endereco: Endereco): Long {
        // TODO: review generated method
        val query = Query()
                .insertInto("endereco")
                .insertEnderecoValues(endereco)

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

    private fun Query.selectEndereco() = selectFields(EnderecoRM.selectFields())

    private fun Query.updateEnderecoSet(endereco: Endereco) = updateSet(EnderecoRM.updateSet(endereco))

    private fun Query.insertEnderecoValues(endereco: Endereco) = insertValues(EnderecoRM.insertValues(endereco))

    private fun Query.whereEnderecoFilter(filter: EnderecoListFilter, alias: String = "endereco"): Query {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(EnderecoRM.fieldsToSearch(alias), "%$it%")
            }
        }

        filter.minNro?.also {
            whereGtEq("$alias.nro", it)
        }
        filter.maxNro?.also {
            whereLtEq("$alias.nro", it)
        }

        filter.minLatitude?.also {
            whereGtEq("$alias.latitude", it)
        }
        filter.maxLatitude?.also {
            whereLtEq("$alias.latitude", it)
        }

        filter.minLongitude?.also {
            whereGtEq("$alias.longitude", it)
        }
        filter.maxLongitude?.also {
            whereLtEq("$alias.longitude", it)
        }

        return this
    }

    private fun Query.orderAndLimitEndereco(filter: EnderecoListFilter, alias: String = "endereco"): Query {
        EnderecoRM.orderMap(alias)[filter.orderBy]?.also {
            orderByAsc(it, filter.ascending)
        }

        filter.limit?.also {
            val index = (filter.page ?: 0) * it
            limit(index, it)
        }

        return this
    }
}
