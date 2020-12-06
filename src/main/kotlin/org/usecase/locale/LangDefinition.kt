package org.usecase.locale

import org.usecase.exception.response.BadRequestException
import org.valiktor.ConstraintViolationException
import java.util.*

open class LangDefinition(bundleName: String) {
    private val bundle: ResourceBundle = ResourceBundle.getBundle(bundleName)

    operator fun get(o: String): String {
        return try {
            bundle.getString(o)
        } catch(e: Exception) {
            o
        }
    }

    fun handleValidation(modelName: String, validation: () -> Unit) {
        try {
            validation()
        } catch (ex: ConstraintViolationException) {
            ex.constraintViolations.first().also {
                val message = "${this["${modelName}.${it.property}"]}: ${this["valiktor.${it.constraint.name}"]}"
                throw BadRequestException(message)
            }
        }
    }
}
