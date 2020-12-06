package org.usecase.extension

import org.valiktor.Constraint
import br.com.simpli.tools.Validator

typealias VProp<E> = org.valiktor.Validator<E>.Property<String?>

object PhoneNumberConstraint : Constraint { override val name = "PhoneNumber" }
fun <E> VProp<E>.isPhoneNumber(): VProp<E> = this.validate(PhoneNumberConstraint) {
    it == null || Validator.isValidPhoneNumber(it)
}

object CelPhoneNumberConstraint : Constraint { override val name = "CelPhoneNumber" }
fun <E> VProp<E>.isCelPhoneNumber(): VProp<E> = this.validate(CelPhoneNumberConstraint) {
    it == null || Validator.isValidCelPhoneNumber(it)
}

object CorporativeEmailConstraint : Constraint { override val name = "CorporativeEmail" }
fun <E> VProp<E>.isCorporativeEmail(): VProp<E> = this.validate(CorporativeEmailConstraint) {
    it == null || Validator.isCorporativeEmail(it)
}

object CPFConstraint : Constraint { override val name = "CPF" }
fun <E> VProp<E>.isCPF(): VProp<E> = this.validate(CPFConstraint) {
    it == null || Validator.isCPF(it)
}

object CNPJConstraint : Constraint { override val name = "CNPJ" }
fun <E> VProp<E>.isCNPJ(): VProp<E> = this.validate(CNPJConstraint) {
    it == null || Validator.isCNPJ(it)
}