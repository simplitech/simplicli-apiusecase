package org.usecase.app.healthcheck

import br.com.simpli.sql.AbstractConnector
import kotlinx.coroutines.*
import org.usecase.exception.response.InternalServerErrorException

/**
 * Health check logic
 * @author Simpli CLI generator
 */
class HealthCheckProcess(val con: AbstractConnector) {

    val dao = HealthCheckDao(con)

    fun check(): Boolean {
        val result = checkDate()
            && checkGlobalScope()
            && checkDefaultDispatcher()
            && checkIODispatcher()
            && checkUnconfinedDispatcher()

        if (!result) throw InternalServerErrorException()

        return result
    }

    private fun checkDate(): Boolean {
        return dao.getDate() != null
    }

    private fun checkGlobalScope(): Boolean {
        return runBlocking { GlobalScope.async { true }.await() }
    }

    private fun checkDefaultDispatcher(): Boolean {
        return runBlocking { CoroutineScope(Dispatchers.Default).async { true }.await() }
    }

    private fun checkIODispatcher(): Boolean {
        return runBlocking { CoroutineScope(Dispatchers.IO).async { true }.await() }
    }

    private fun checkUnconfinedDispatcher(): Boolean {
        return runBlocking { CoroutineScope(Dispatchers.Unconfined).async { true }.await() }
    }


}
