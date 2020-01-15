package org.usecase.locale

import br.com.simpli.model.PortugueseLanguage

/**
 * Brazilian Portuguese language
 * @author Simpli CLI generator
 */
class PtBr : PortugueseLanguage() {
    init {
        dictionary = hashMapOf(
                "email_sender_name" to "Equipe Usecase",
                "email_contact" to "contact@usecase.org",

                "email_reset_password_subject" to "Esqueci minha senha",
                "email_reset_password_title" to "Esqueci minha senha",
                "email_reset_password_subtitle" to "As vezes isso pode acontecer. Não tem problema!",
                "email_reset_password_body" to "",
                "email_reset_password_text_button" to "Se você solicitou o esqueci senha, clique no botão abaixo para criar uma nova.",
                "email_reset_password_label_button" to "Criar nova senha",

                "access_denied" to "Acesso Negado",
                "already_exists" to "Não é possível criar um item já existente",
                "does_not_exist" to "Não é possível editar um item não existente",
                "invalid_token" to "Token Inválido",
                "user_id_not_found" to "ID do Usuário Não Encontrado",
                "user_not_found" to "Usuário Não Encontrado",
                "wrong_password" to "Senha inválida",
                "password_no_match" to "Os campos senha precisam ser iguais",
                "something_went_wrong" to "Algo saiu errado. Contate o admin!",

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
