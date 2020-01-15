package org.usecase.locale

import br.com.simpli.model.EnglishLanguage

/**
 * USA English language
 * @author Simpli CLI generator
 */
class EnUs : EnglishLanguage() {
    init {
        dictionary = hashMapOf(
                "email_sender_name" to "Usecase staff",
                "email_contact" to "contact@usecase.org",

                "email_reset_password_subject" to "Forgot my password",
                "email_reset_password_title" to "Forgot my password",
                "email_reset_password_subtitle" to "It happens. Do not worry!",
                "email_reset_password_body" to "",
                "email_reset_password_text_button" to "If you have solicited to change the password, click in the button below in order to create a new one.",
                "email_reset_password_label_button" to "Create new password",

                "access_denied" to "Access Denied",
                "already_exists" to "It is not possible to create an already existing item",
                "does_not_exist" to "It is not possible to edit a non-existing item",
                "invalid_token" to "Invalid Token",
                "user_id_not_found" to "UserRouter ID Not Found",
                "user_not_found" to "UserRouter Not Found",
                "wrong_password" to "Wrong password",
                "password_no_match" to "The field password must match",
                "something_went_wrong" to "Something wend wrong. Contact the admin!",

                /**
                 * Conectado
                 */
                "Conectado.idConectadoPk" to "Id Conectado Pk",
                "Conectado.titulo" to "Titulo",

                /**
                 * ConectorPrincipal
                 */
                "ConectorPrincipal.idPrincipalFk" to "Id Principal Fk",
                "ConectorPrincipal.idConectadoFk" to "Id Conectado Fk",
                "ConectorPrincipal.titulo" to "Titulo",

                /**
                 * Endereco
                 */
                "Endereco.idEnderecoPk" to "Id Endereco Pk",
                "Endereco.cep" to "Cep",
                "Endereco.zipcode" to "Zipcode",
                "Endereco.rua" to "Rua",
                "Endereco.nro" to "Nro",
                "Endereco.cidade" to "Cidade",
                "Endereco.uf" to "Uf",
                "Endereco.latitude" to "Latitude",
                "Endereco.longitude" to "Longitude",

                /**
                 * ExtensaoDoPrincipal
                 */
                "ExtensaoDoPrincipal.idPrincipalFk" to "Id Principal Fk",
                "ExtensaoDoPrincipal.titulo" to "Titulo",

                /**
                 * GrupoDoPrincipal
                 */
                "GrupoDoPrincipal.idGrupoDoPrincipalPk" to "Id Grupo Do Principal Pk",
                "GrupoDoPrincipal.titulo" to "Titulo",

                /**
                 * ItemDoPrincipal
                 */
                "ItemDoPrincipal.idItemDoPrincipalPk" to "Id Item Do Principal Pk",
                "ItemDoPrincipal.titulo" to "Titulo",
                "ItemDoPrincipal.idPrincipalFk" to "Id Principal Fk",

                /**
                 * Principal
                 */
                "Principal.idPrincipalPk" to "Id Principal Pk",
                "Principal.textoObrigatorio" to "Texto Obrigatorio",
                "Principal.textoFacultativo" to "Texto Facultativo",
                "Principal.decimalObrigatorio" to "Decimal Obrigatorio",
                "Principal.decimalFacultativo" to "Decimal Facultativo",
                "Principal.inteiroObrigatorio" to "Inteiro Obrigatorio",
                "Principal.inteiroFacultativo" to "Inteiro Facultativo",
                "Principal.booleanoObrigatorio" to "Booleano Obrigatorio",
                "Principal.booleanoFacultativo" to "Booleano Facultativo",
                "Principal.dataObrigatoria" to "Data Obrigatoria",
                "Principal.dataFacultativa" to "Data Facultativa",
                "Principal.datahoraObrigatoria" to "Datahora Obrigatoria",
                "Principal.datahoraFacultativa" to "Datahora Facultativa",
                "Principal.ativo" to "Ativo",
                "Principal.email" to "Email",
                "Principal.senha" to "Senha",
                "Principal.urlImagem" to "Url Imagem",
                "Principal.url" to "Url",
                "Principal.idGrupoDoPrincipalFk" to "Id Grupo Do Principal Fk",
                "Principal.idGrupoDoPrincipalFacultativoFk" to "Id Grupo Do Principal Facultativo Fk",
                "Principal.unico" to "Unico",
                "Principal.dataCriacao" to "Data Criacao",
                "Principal.dataAlteracao" to "Data Alteracao",
                "Principal.nome" to "Nome",
                "Principal.titulo" to "Titulo",
                "Principal.cpf" to "Cpf",
                "Principal.cnpj" to "Cnpj",
                "Principal.rg" to "Rg",
                "Principal.celular" to "Celular",
                "Principal.textoGrande" to "Texto Grande",
                "Principal.snakeCase" to "Snake Case",
                "Principal.preco" to "Preco",

                /**
                 * Tag
                 */
                "Tag.idTagPk" to "Id Tag Pk",
                "Tag.titulo" to "Titulo",

                /**
                 * TagPrincipal
                 */
                "TagPrincipal.idPrincipalFk" to "Id Principal Fk",
                "TagPrincipal.idTagFk" to "Id Tag Fk",

                /**
                 * User
                 */
                "User.idUserPk" to "Id User Pk",
                "User.email" to "Email",
                "User.senha" to "Senha"
        )
    }
}
