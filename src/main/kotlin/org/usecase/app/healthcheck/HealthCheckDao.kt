package org.usecase.app.healthcheck

import br.com.simpli.sql.AbstractConnector
import br.com.simpli.sql.Query
import java.util.*

/**
 * Data Access Object of Health Check
 * @author Simpli CLI generator
 */
class HealthCheckDao(val con: AbstractConnector) {
    fun getDate(): Date? {
        val query = Query().selectRaw("NOW()")

        return con.getFirstDate(query)
    }
}
