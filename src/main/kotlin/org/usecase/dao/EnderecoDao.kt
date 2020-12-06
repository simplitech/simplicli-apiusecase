package org.usecase.dao

import org.usecase.model.filter.EnderecoListFilter
import org.usecase.model.resource.Endereco
import org.usecase.model.rm.EnderecoRM
import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import br.com.simpli.sql.VirtualSelect
import org.usecase.user.context.Permission

/**
 * Data Access Object of Endereco from table endereco
 * @author Simpli CLI generator
 */
class EnderecoDao(val con: AbstractConnector) {
    fun getOne(idEnderecoPk: Long, permission: Permission): Endereco? {
        val enderecoRm = EnderecoRM(permission)

        val vs = VirtualSelect()
                .selectFields(enderecoRm.selectFields)
                .from(enderecoRm)
                .whereEq(enderecoRm.idEnderecoPk, idEnderecoPk)

        return con.getOne(vs.toQuery()) {
            enderecoRm.build(it)
        }
    }

    fun getList(filter: EnderecoListFilter, permission: Permission): MutableList<Endereco> {
        val enderecoRm = EnderecoRM(permission)

        val vs = VirtualSelect()
                .selectFields(enderecoRm.selectFields)
                .from(enderecoRm)
                .whereEnderecoFilter(enderecoRm, filter)
                .orderAndLimitEndereco(enderecoRm, filter)

        return con.getList(vs.toQuery()) {
            enderecoRm.build(it)
        }
    }

    fun count(filter: EnderecoListFilter, permission: Permission): Int {
        val enderecoRm = EnderecoRM(permission)

        val vs = VirtualSelect()
                .selectRaw("COUNT(DISTINCT %s)", enderecoRm.idEnderecoPk)
                .from(enderecoRm)
                .whereEnderecoFilter(enderecoRm, filter)

        return con.getFirstInt(vs.toQuery()) ?: 0
    }

    fun update(endereco: Endereco, permission: Permission): Int {
        val enderecoRm = EnderecoRM(permission)
        val query = Query()
                .updateTable(enderecoRm.table)
                .updateSet(enderecoRm.updateSet(endereco))
                .whereEq(enderecoRm.idEnderecoPk.column, endereco.id)

        return con.execute(query).affectedRows
    }

    fun insert(endereco: Endereco, permission: Permission): Long {
        val enderecoRm = EnderecoRM(permission)
        val query = Query()
                .insertInto(enderecoRm.table)
                .insertValues(enderecoRm.insertValues(endereco))

        return con.execute(query).key
    }

    fun exist(idEnderecoPk: Long, permission: Permission): Boolean {
        val enderecoRm = EnderecoRM(permission)
        val vs = VirtualSelect()
                .select(enderecoRm.idEnderecoPk)
                .from(enderecoRm)
                .whereEq(enderecoRm.idEnderecoPk, idEnderecoPk)

        return con.exist(vs.toQuery())
    }

    private fun VirtualSelect.whereEnderecoFilter(enderecoRm: EnderecoRM, filter: EnderecoListFilter): VirtualSelect {
        filter.query?.also {
            if (it.isNotEmpty()) {
                whereSomeLikeThis(enderecoRm.fieldsToSearch, "%$it%")
            }
        }

        filter.minNro?.also {
            whereGtEq(enderecoRm.nro, it)
        }
        filter.maxNro?.also {
            whereLtEq(enderecoRm.nro, it)
        }

        filter.minLatitude?.also {
            whereGtEq(enderecoRm.latitude, it)
        }
        filter.maxLatitude?.also {
            whereLtEq(enderecoRm.latitude, it)
        }

        filter.minLongitude?.also {
            whereGtEq(enderecoRm.longitude, it)
        }
        filter.maxLongitude?.also {
            whereLtEq(enderecoRm.longitude, it)
        }

        return this
    }

    private fun VirtualSelect.orderAndLimitEndereco(enderecoRm: EnderecoRM, filter: EnderecoListFilter): VirtualSelect {
        orderBy(enderecoRm.orderMap, filter.orderBy to filter.ascending)

        limitByPage(filter.page, filter.limit)

        return this
    }
}
