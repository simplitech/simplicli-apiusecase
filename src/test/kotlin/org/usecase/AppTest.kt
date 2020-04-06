package org.usecase

import org.usecase.param.DefaultParam
import br.com.simpli.sql.DaoTest
import br.com.simpli.sql.TransacConnector

/**
 * Extended class of database connector tests
 * @author Simpli CLI generator
 */
open class AppTest : DaoTest() {
    protected val transacConnector = TransacConnector(getConnection())
    protected val param = DefaultParam()
}
