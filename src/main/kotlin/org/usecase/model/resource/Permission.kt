package org.usecase.model.resource

import io.swagger.v3.oas.annotations.media.Schema
import org.usecase.context.PermissionGroup

/**
 * Reference model of table permission
 *
 * @author Simpli CLI generator
 */
class Permission : PermissionGroup() {
    @Schema(required = true) var idPermissionPk: Long = 0

    var permissions: List<Permission>? = null
    var roles: List<Role>? = null
    var users: List<User>? = null

    @Schema(required = true, maxLength = 127) var scope: String? = null

    @Schema(maxLength = 127) var name: String? = null
    @Schema(maxLength = 255) var description: String? = null

    @Schema(required = true) var active: Boolean? = null

    var id
        @Schema(hidden = true)
        get() = idPermissionPk
        set(value) {
            idPermissionPk = value
        }

    override val scopes: MutableList<String>
        @Schema(hidden = true)
        get() = (permissions ?: emptyList())
                .map { it.scopes }
                .flatten()
                .toMutableList()
                .apply {
                    scope?.also { add(it) }
                }
                .distinct()
                .toMutableList()

    companion object {
        fun groupOf (vararg scopes: String) = PermissionGroup(scopes.toMutableList())

        const val FULL_CONTROL = "full-control"

        // FULL_CONTROL
        const val CONECTADO_FULL_CONTROL = "conectado-full-control"
        const val CONECTOR_PRINCIPAL_FULL_CONTROL = "conector-principal-full-control"
        const val ENDERECO_FULL_CONTROL = "endereco-full-control"
        const val EXTENSAO_DO_PRINCIPAL_FULL_CONTROL = "extensao-do-principal-full-control"
        const val GRUPO_DO_PRINCIPAL_FULL_CONTROL = "grupo-do-principal-full-control"
        const val ITEM_DO_PRINCIPAL_FULL_CONTROL = "item-do-principal-full-control"
        const val PERMISSION_FULL_CONTROL = "permission-full-control"
        const val PERMISSION_PERMISSION_FULL_CONTROL = "permission-permission-full-control"
        const val PRINCIPAL_FULL_CONTROL = "principal-full-control"
        const val ROLE_FULL_CONTROL = "role-full-control"
        const val ROLE_PERMISSION_FULL_CONTROL = "role-permission-full-control"
        const val TAG_PRINCIPAL_FULL_CONTROL = "tag-principal-full-control"
        const val TAG_FULL_CONTROL = "tag-full-control"
        const val USER_FULL_CONTROL = "user-full-control"
        const val USER_PERMISSION_FULL_CONTROL = "user-permission-full-control"
        const val USER_ROLE_FULL_CONTROL = "user-role-full-control"

        // READ_ALL
        const val CONECTADO_READ_ALL = "conectado-read-all"
        const val CONECTOR_PRINCIPAL_READ_ALL = "conector-principal-read-all"
        const val ENDERECO_READ_ALL = "endereco-read-all"
        const val EXTENSAO_DO_PRINCIPAL_READ_ALL = "extensao-do-principal-read-all"
        const val GRUPO_DO_PRINCIPAL_READ_ALL = "grupo-do-principal-read-all"
        const val ITEM_DO_PRINCIPAL_READ_ALL = "item-do-principal-read-all"
        const val PERMISSION_READ_ALL = "permission-read-all"
        const val PERMISSION_PERMISSION_READ_ALL = "permission-permission-read-all"
        const val PRINCIPAL_READ_ALL = "principal-read-all"
        const val ROLE_READ_ALL = "role-read-all"
        const val ROLE_PERMISSION_READ_ALL = "role-permission-read-all"
        const val TAG_PRINCIPAL_READ_ALL = "tag-principal-read-all"
        const val TAG_READ_ALL = "tag-read-all"
        const val USER_READ_ALL = "user-read-all"
        const val USER_PERMISSION_READ_ALL = "user-permission-read-all"
        const val USER_ROLE_READ_ALL = "user-role-read-all"

        // INSERT_ALL
        const val CONECTADO_INSERT_ALL = "conectado-insert-all"
        const val CONECTOR_PRINCIPAL_INSERT_ALL = "conector-principal-insert-all"
        const val ENDERECO_INSERT_ALL = "endereco-insert-all"
        const val EXTENSAO_DO_PRINCIPAL_INSERT_ALL = "extensao-do-principal-insert-all"
        const val GRUPO_DO_PRINCIPAL_INSERT_ALL = "grupo-do-principal-insert-all"
        const val ITEM_DO_PRINCIPAL_INSERT_ALL = "item-do-principal-insert-all"
        const val PERMISSION_INSERT_ALL = "permission-insert-all"
        const val PERMISSION_PERMISSION_INSERT_ALL = "permission-permission-insert-all"
        const val PRINCIPAL_INSERT_ALL = "principal-insert-all"
        const val ROLE_INSERT_ALL = "role-insert-all"
        const val ROLE_PERMISSION_INSERT_ALL = "role-permission-insert-all"
        const val TAG_PRINCIPAL_INSERT_ALL = "tag-principal-insert-all"
        const val TAG_INSERT_ALL = "tag-insert-all"
        const val USER_INSERT_ALL = "user-insert-all"
        const val USER_PERMISSION_INSERT_ALL = "user-permission-insert-all"
        const val USER_ROLE_INSERT_ALL = "user-role-insert-all"

        // UPDATE_ALL
        const val CONECTADO_UPDATE_ALL = "conectado-update-all"
        const val CONECTOR_PRINCIPAL_UPDATE_ALL = "conector-principal-update-all"
        const val ENDERECO_UPDATE_ALL = "endereco-update-all"
        const val EXTENSAO_DO_PRINCIPAL_UPDATE_ALL = "extensao-do-principal-update-all"
        const val GRUPO_DO_PRINCIPAL_UPDATE_ALL = "grupo-do-principal-update-all"
        const val ITEM_DO_PRINCIPAL_UPDATE_ALL = "item-do-principal-update-all"
        const val PERMISSION_UPDATE_ALL = "permission-update-all"
        const val PERMISSION_PERMISSION_UPDATE_ALL = "permission-permission-update-all"
        const val PRINCIPAL_UPDATE_ALL = "principal-update-all"
        const val ROLE_UPDATE_ALL = "role-update-all"
        const val ROLE_PERMISSION_UPDATE_ALL = "role-permission-update-all"
        const val TAG_PRINCIPAL_UPDATE_ALL = "tag-principal-update-all"
        const val TAG_UPDATE_ALL = "tag-update-all"
        const val USER_UPDATE_ALL = "user-update-all"
        const val USER_PERMISSION_UPDATE_ALL = "user-permission-update-all"
        const val USER_ROLE_UPDATE_ALL = "user-role-update-all"

        // DELETE
        const val CONECTADO_DELETE = "conectado-delete"
        const val CONECTOR_PRINCIPAL_DELETE = "conector-principal-delete"
        const val ENDERECO_DELETE = "endereco-delete"
        const val EXTENSAO_DO_PRINCIPAL_DELETE = "extensao-do-principal-delete"
        const val GRUPO_DO_PRINCIPAL_DELETE = "grupo-do-principal-delete"
        const val ITEM_DO_PRINCIPAL_DELETE = "item-do-principal-delete"
        const val PERMISSION_DELETE = "permission-delete"
        const val PERMISSION_PERMISSION_DELETE = "permission-permission-delete"
        const val PRINCIPAL_DELETE = "principal-delete"
        const val ROLE_DELETE = "role-delete"
        const val ROLE_PERMISSION_DELETE = "role-permission-delete"
        const val TAG_PRINCIPAL_DELETE = "tag-principal-delete"
        const val TAG_DELETE = "tag-delete"
        const val USER_DELETE = "user-delete"
        const val USER_PERMISSION_DELETE = "user-permission-delete"
        const val USER_ROLE_DELETE = "user-role-delete"

        // CONECTADO
        const val CONECTADO_READ_ID_CONECTADO_PK = "conectado-read-id-conectado-pk"
        const val CONECTADO_READ_TITULO = "conectado-read-id-titulo"

        const val CONECTADO_INSERT_ID_CONECTADO_PK = "conectado-insert-id-conectado-pk"
        const val CONECTADO_INSERT_TITULO = "conectado-insert-id-titulo"

        const val CONECTADO_UPDATE_ID_CONECTADO_PK = "conectado-update-id-conectado-pk"
        const val CONECTADO_UPDATE_TITULO = "conectado-update-id-titulo"

        // CONECTADO_PRINCIPAL
        const val CONECTOR_PRINCIPAL_READ_TITULO = "conector-principal-read-titulo"
        const val CONECTOR_PRINCIPAL_READ_ID_PRINCIPAL_FK = "conector-principal-read-id-principal-fk"
        const val CONECTOR_PRINCIPAL_READ_ID_CONECTADO_FK = "conector-principal-read-id-conectado-fk"

        const val CONECTOR_PRINCIPAL_INSERT_TITULO = "conector-principal-insert-titulo"
        const val CONECTOR_PRINCIPAL_INSERT_ID_PRINCIPAL_FK = "conector-principal-insert-id-principal-fk"
        const val CONECTOR_PRINCIPAL_INSERT_ID_CONECTADO_FK = "conector-principal-insert-id-conectado-fk"

        const val CONECTOR_PRINCIPAL_UPDATE_TITULO = "conector-principal-update-titulo"
        const val CONECTOR_PRINCIPAL_UPDATE_ID_PRINCIPAL_FK = "conector-principal-update-id-principal-fk"
        const val CONECTOR_PRINCIPAL_UPDATE_ID_CONECTADO_FK = "conector-principal-update-id-conectado-fk"

        // ENDERECO
        const val ENDERECO_READ_ID_ENDERECO_PK = "endereco-read-id-endereco-pk"
        const val ENDERECO_READ_CEP = "endereco-read-cep"
        const val ENDERECO_READ_ZIPCODE = "endereco-read-zipcode"
        const val ENDERECO_READ_RUA = "endereco-read-rua"
        const val ENDERECO_READ_CIDADE = "endereco-read-cidade"
        const val ENDERECO_READ_UF = "endereco-read-uf"
        const val ENDERECO_READ_NRO = "endereco-read-nro"
        const val ENDERECO_READ_LATITUDE = "endereco-read-latitude"
        const val ENDERECO_READ_LONGITUDE = "endereco-read-longitude"

        const val ENDERECO_INSERT_ID_ENDERECO_PK = "endereco-insert-id-endereco-pk"
        const val ENDERECO_INSERT_CEP = "endereco-insert-cep"
        const val ENDERECO_INSERT_ZIPCODE = "endereco-insert-zipcode"
        const val ENDERECO_INSERT_RUA = "endereco-insert-rua"
        const val ENDERECO_INSERT_CIDADE = "endereco-insert-cidade"
        const val ENDERECO_INSERT_UF = "endereco-insert-uf"
        const val ENDERECO_INSERT_NRO = "endereco-insert-nro"
        const val ENDERECO_INSERT_LATITUDE = "endereco-insert-latitude"
        const val ENDERECO_INSERT_LONGITUDE = "endereco-insert-longitude"

        const val ENDERECO_UPDATE_ID_ENDERECO_PK = "endereco-update-id-endereco-pk"
        const val ENDERECO_UPDATE_CEP = "endereco-update-cep"
        const val ENDERECO_UPDATE_ZIPCODE = "endereco-update-zipcode"
        const val ENDERECO_UPDATE_RUA = "endereco-update-rua"
        const val ENDERECO_UPDATE_CIDADE = "endereco-update-cidade"
        const val ENDERECO_UPDATE_UF = "endereco-update-uf"
        const val ENDERECO_UPDATE_NRO = "endereco-update-nro"
        const val ENDERECO_UPDATE_LATITUDE = "endereco-update-latitude"
        const val ENDERECO_UPDATE_LONGITUDE = "endereco-update-longitude"

        // EXTENSAO_DO_PRINCIPAL
        const val EXTENSAO_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK = "extensao-do-principal-read-id-principal-fk"
        const val EXTENSAO_DO_PRINCIPAL_READ_TITULO = "extensao-do-principal-read-titulo"

        const val EXTENSAO_DO_PRINCIPAL_INSERT_ID_PRINCIPAL_FK = "extensao-do-principal-insert-id-principal-fk"
        const val EXTENSAO_DO_PRINCIPAL_INSERT_TITULO = "extensao-do-principal-insert-titulo"

        const val EXTENSAO_DO_PRINCIPAL_UPDATE_ID_PRINCIPAL_FK = "extensao-do-principal-update-id-principal-fk"
        const val EXTENSAO_DO_PRINCIPAL_UPDATE_TITULO = "extensao-do-principal-update-titulo"

        // GRUPO_DO_PRINCIPAL
        const val GRUPO_DO_PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_PK = "grupo-do-principal-read-id-grupo-do-principal-pk"
        const val GRUPO_DO_PRINCIPAL_READ_TITULO = "grupo-do-principal-read-titulo"

        const val GRUPO_DO_PRINCIPAL_INSERT_ID_GRUPO_DO_PRINCIPAL_PK = "grupo-do-principal-insert-id-grupo-do-principal-pk"
        const val GRUPO_DO_PRINCIPAL_INSERT_TITULO = "grupo-do-principal-insert-titulo"

        const val GRUPO_DO_PRINCIPAL_UPDATE_ID_GRUPO_DO_PRINCIPAL_PK = "grupo-do-principal-update-id-grupo-do-principal-pk"
        const val GRUPO_DO_PRINCIPAL_UPDATE_TITULO = "grupo-do-principal-update-titulo"

        // ITEM_DO_PRINCIPAL
        const val ITEM_DO_PRINCIPAL_READ_ID_ITEM_DO_PRINCIPAL_PK = "item-do-principal-read-id-item-do-principal-pk"
        const val ITEM_DO_PRINCIPAL_READ_ID_PRINCIPAL_FK = "item-do-principal-read-id-principal-fk"
        const val ITEM_DO_PRINCIPAL_READ_TITULO = "item-do-principal-read-titulo"

        const val ITEM_DO_PRINCIPAL_INSERT_ID_ITEM_DO_PRINCIPAL_PK = "item-do-principal-insert-id-item-do-principal-pk"
        const val ITEM_DO_PRINCIPAL_INSERT_ID_PRINCIPAL_FK = "item-do-principal-insert-id-principal-fk"
        const val ITEM_DO_PRINCIPAL_INSERT_TITULO = "item-do-principal-insert-titulo"

        const val ITEM_DO_PRINCIPAL_UPDATE_ID_ITEM_DO_PRINCIPAL_PK = "item-do-principal-update-id-item-do-principal-pk"
        const val ITEM_DO_PRINCIPAL_UPDATE_ID_PRINCIPAL_FK = "item-do-principal-update-id-principal-fk"
        const val ITEM_DO_PRINCIPAL_UPDATE_TITULO = "item-do-principal-update-titulo"

        // PERMISSION
        const val PERMISSION_READ_ID_PERMISSION_PK = "permission-read-id-permission-pk"
        const val PERMISSION_READ_SCOPE = "permission-read-scope"
        const val PERMISSION_READ_NAME = "permission-read-name"
        const val PERMISSION_READ_DESCRIPTION = "permission-read-description"
        const val PERMISSION_READ_ACTIVE = "permission-read-active"

        const val PERMISSION_INSERT_ID_PERMISSION_PK = "permission-insert-id-permission-pk"
        const val PERMISSION_INSERT_SCOPE = "permission-insert-scope"
        const val PERMISSION_INSERT_NAME = "permission-insert-name"
        const val PERMISSION_INSERT_DESCRIPTION = "permission-insert-description"
        const val PERMISSION_INSERT_ACTIVE = "permission-insert-active"

        const val PERMISSION_UPDATE_ID_PERMISSION_PK = "permission-update-id-permission-pk"
        const val PERMISSION_UPDATE_SCOPE = "permission-update-scope"
        const val PERMISSION_UPDATE_NAME = "permission-update-name"
        const val PERMISSION_UPDATE_DESCRIPTION = "permission-update-description"
        const val PERMISSION_UPDATE_ACTIVE = "permission-update-active"

        // PERMISSION_PERMISSION
        const val PERMISSION_PERMISSION_READ_ID_PERMISSION_PARENT_FK = "permission-permission-read-id-permission-parent-fk"
        const val PERMISSION_PERMISSION_READ_ID_PERMISSION_CHILD_FK = "permission-permission-read-id-permission-child-fk"

        const val PERMISSION_PERMISSION_INSERT_ID_PERMISSION_PARENT_FK = "permission-permission-insert-id-permission-parent-fk"
        const val PERMISSION_PERMISSION_INSERT_ID_PERMISSION_CHILD_FK = "permission-permission-insert-id-permission-child-fk"

        const val PERMISSION_PERMISSION_UPDATE_ID_PERMISSION_PARENT_FK = "permission-permission-update-id-permission-parent-fk"
        const val PERMISSION_PERMISSION_UPDATE_ID_PERMISSION_CHILD_FK = "permission-permission-update-id-permission-child-fk"

        // PRINCIPAL
        const val PRINCIPAL_READ_ID_PRINCIPAL_PK = "principal-read-id-principal-pk"
        const val PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FK = "principal-read-id-grupo-do-principal-fk"
        const val PRINCIPAL_READ_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK = "principal-read-id-grupo-do-principal-facultativo-fk"
        const val PRINCIPAL_READ_TEXTO_OBRIGATORIO = "principal-read-texto-obrigatorio"
        const val PRINCIPAL_READ_UNICO = "principal-read-unico"
        const val PRINCIPAL_READ_DECIMAL_OBRIGATORIO = "principal-read-decimal-obrigatorio"
        const val PRINCIPAL_READ_INTEIRO_OBRIGATORIO = "principal-read-inteiro-obrigatorio"
        const val PRINCIPAL_READ_BOOLEANO_OBRIGATORIO = "principal-read-booleano-obrigatorio"
        const val PRINCIPAL_READ_DATA_OBRIGATORIA = "principal-read-data-obrigatoria"
        const val PRINCIPAL_READ_DATAHORA_OBRIGATORIA = "principal-read-datahora-obrigatoris"
        const val PRINCIPAL_READ_ATIVO = "principal-read-ativo"
        const val PRINCIPAL_READ_DATA_CRIACAO = "principal-read-data-criacao"
        const val PRINCIPAL_READ_TEXTO_FACULTATIVO = "principal-read-texto-facultativo"
        const val PRINCIPAL_READ_EMAIL = "principal-read-email"
        const val PRINCIPAL_READ_URL_IMAGEM = "principal-read-url-imagem"
        const val PRINCIPAL_READ_URL = "principal-read-url"
        const val PRINCIPAL_READ_NOME = "principal-read-nome"
        const val PRINCIPAL_READ_TITULO = "principal-read-titulo"
        const val PRINCIPAL_READ_CPF = "principal-read-cpf"
        const val PRINCIPAL_READ_CNPJ = "principal-read-cnpj"
        const val PRINCIPAL_READ_RG = "principal-read-rg"
        const val PRINCIPAL_READ_CELULAR = "principal-read-celular"
        const val PRINCIPAL_READ_TEXTO_GRANDE = "principal-read-texto-grande"
        const val PRINCIPAL_READ_SNAKE_CASE = "principal-read-snake-case"
        const val PRINCIPAL_READ_DECIMAL_FACULTATIVO = "principal-read-decimal-facultativo"
        const val PRINCIPAL_READ_INTEIRO_FACULTATIVO = "principal-read-interio-facultativo"
        const val PRINCIPAL_READ_BOOLEANO_FACULTATIVO = "principal-read-booleano-facultativo"
        const val PRINCIPAL_READ_DATA_FACULTATIVA = "principal-read-data-facultativa"
        const val PRINCIPAL_READ_DATAHORA_FACULTATIVA = "principal-read-datahora-facultativa"
        const val PRINCIPAL_READ_DATA_ALTERACAO = "principal-read-data-alteracao"
        const val PRINCIPAL_READ_PRECO = "principal-read-preco"
        const val PRINCIPAL_READ_SENHA = "principal-read-senha"

        const val PRINCIPAL_INSERT_ID_PRINCIPAL_PK = "principal-insert-id-principal-pk"
        const val PRINCIPAL_INSERT_ID_GRUPO_DO_PRINCIPAL_FK = "principal-insert-id-grupo-do-principal-fk"
        const val PRINCIPAL_INSERT_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK = "principal-insert-id-grupo-do-principal-facultativo-fk"
        const val PRINCIPAL_INSERT_TEXTO_OBRIGATORIO = "principal-insert-texto-obrigatorio"
        const val PRINCIPAL_INSERT_UNICO = "principal-insert-unico"
        const val PRINCIPAL_INSERT_DECIMAL_OBRIGATORIO = "principal-insert-decimal-obrigatorio"
        const val PRINCIPAL_INSERT_INTEIRO_OBRIGATORIO = "principal-insert-inteiro-obrigatorio"
        const val PRINCIPAL_INSERT_BOOLEANO_OBRIGATORIO = "principal-insert-booleano-obrigatorio"
        const val PRINCIPAL_INSERT_DATA_OBRIGATORIA = "principal-insert-data-obrigatoria"
        const val PRINCIPAL_INSERT_DATAHORA_OBRIGATORIA = "principal-insert-datahora-obrigatoris"
        const val PRINCIPAL_INSERT_ATIVO = "principal-insert-ativo"
        const val PRINCIPAL_INSERT_DATA_CRIACAO = "principal-insert-data-criacao"
        const val PRINCIPAL_INSERT_TEXTO_FACULTATIVO = "principal-insert-texto-facultativo"
        const val PRINCIPAL_INSERT_EMAIL = "principal-insert-email"
        const val PRINCIPAL_INSERT_URL_IMAGEM = "principal-insert-url-imagem"
        const val PRINCIPAL_INSERT_URL = "principal-insert-url"
        const val PRINCIPAL_INSERT_NOME = "principal-insert-nome"
        const val PRINCIPAL_INSERT_TITULO = "principal-insert-titulo"
        const val PRINCIPAL_INSERT_CPF = "principal-insert-cpf"
        const val PRINCIPAL_INSERT_CNPJ = "principal-insert-cnpj"
        const val PRINCIPAL_INSERT_RG = "principal-insert-rg"
        const val PRINCIPAL_INSERT_CELULAR = "principal-insert-celular"
        const val PRINCIPAL_INSERT_TEXTO_GRANDE = "principal-insert-texto-grande"
        const val PRINCIPAL_INSERT_SNAKE_CASE = "principal-insert-snake-case"
        const val PRINCIPAL_INSERT_DECIMAL_FACULTATIVO = "principal-insert-decimal-facultativo"
        const val PRINCIPAL_INSERT_INTEIRO_FACULTATIVO = "principal-insert-interio-facultativo"
        const val PRINCIPAL_INSERT_BOOLEANO_FACULTATIVO = "principal-insert-booleano-facultativo"
        const val PRINCIPAL_INSERT_DATA_FACULTATIVA = "principal-insert-data-facultativa"
        const val PRINCIPAL_INSERT_DATAHORA_FACULTATIVA = "principal-insert-datahora-facultativa"
        const val PRINCIPAL_INSERT_DATA_ALTERACAO = "principal-insert-data-alteracao"
        const val PRINCIPAL_INSERT_PRECO = "principal-insert-preco"
        const val PRINCIPAL_INSERT_SENHA = "principal-insert-senha"

        const val PRINCIPAL_UPDATE_ID_PRINCIPAL_PK = "principal-update-id-principal-pk"
        const val PRINCIPAL_UPDATE_ID_GRUPO_DO_PRINCIPAL_FK = "principal-update-id-grupo-do-principal-fk"
        const val PRINCIPAL_UPDATE_ID_GRUPO_DO_PRINCIPAL_FACULTATIVO_FK = "principal-update-id-grupo-do-principal-facultativo-fk"
        const val PRINCIPAL_UPDATE_TEXTO_OBRIGATORIO = "principal-update-texto-obrigatorio"
        const val PRINCIPAL_UPDATE_UNICO = "principal-update-unico"
        const val PRINCIPAL_UPDATE_DECIMAL_OBRIGATORIO = "principal-update-decimal-obrigatorio"
        const val PRINCIPAL_UPDATE_INTEIRO_OBRIGATORIO = "principal-update-inteiro-obrigatorio"
        const val PRINCIPAL_UPDATE_BOOLEANO_OBRIGATORIO = "principal-update-booleano-obrigatorio"
        const val PRINCIPAL_UPDATE_DATA_OBRIGATORIA = "principal-update-data-obrigatoria"
        const val PRINCIPAL_UPDATE_DATAHORA_OBRIGATORIA = "principal-update-datahora-obrigatoris"
        const val PRINCIPAL_UPDATE_ATIVO = "principal-update-ativo"
        const val PRINCIPAL_UPDATE_DATA_CRIACAO = "principal-update-data-criacao"
        const val PRINCIPAL_UPDATE_TEXTO_FACULTATIVO = "principal-update-texto-facultativo"
        const val PRINCIPAL_UPDATE_EMAIL = "principal-update-email"
        const val PRINCIPAL_UPDATE_URL_IMAGEM = "principal-update-url-imagem"
        const val PRINCIPAL_UPDATE_URL = "principal-update-url"
        const val PRINCIPAL_UPDATE_NOME = "principal-update-nome"
        const val PRINCIPAL_UPDATE_TITULO = "principal-update-titulo"
        const val PRINCIPAL_UPDATE_CPF = "principal-update-cpf"
        const val PRINCIPAL_UPDATE_CNPJ = "principal-update-cnpj"
        const val PRINCIPAL_UPDATE_RG = "principal-update-rg"
        const val PRINCIPAL_UPDATE_CELULAR = "principal-update-celular"
        const val PRINCIPAL_UPDATE_TEXTO_GRANDE = "principal-update-texto-grande"
        const val PRINCIPAL_UPDATE_SNAKE_CASE = "principal-update-snake-case"
        const val PRINCIPAL_UPDATE_DECIMAL_FACULTATIVO = "principal-update-decimal-facultativo"
        const val PRINCIPAL_UPDATE_INTEIRO_FACULTATIVO = "principal-update-interio-facultativo"
        const val PRINCIPAL_UPDATE_BOOLEANO_FACULTATIVO = "principal-update-booleano-facultativo"
        const val PRINCIPAL_UPDATE_DATA_FACULTATIVA = "principal-update-data-facultativa"
        const val PRINCIPAL_UPDATE_DATAHORA_FACULTATIVA = "principal-update-datahora-facultativa"
        const val PRINCIPAL_UPDATE_DATA_ALTERACAO = "principal-update-data-alteracao"
        const val PRINCIPAL_UPDATE_PRECO = "principal-update-preco"
        const val PRINCIPAL_UPDATE_SENHA = "principal-update-senha"

        // ROLE
        const val ROLE_READ_ID_ROLE_PK = "role-read-id-role-pk"
        const val ROLE_READ_SLUG = "role-read-slug"
        const val ROLE_READ_NAME = "role-read-name"
        const val ROLE_READ_DESCRIPTION = "role-read-description"
        const val ROLE_READ_LEVEL = "role-read-level"
        const val ROLE_READ_ACTIVE = "role-read-active"

        const val ROLE_INSERT_ID_ROLE_PK = "role-insert-id-role-pk"
        const val ROLE_INSERT_SLUG = "role-insert-slug"
        const val ROLE_INSERT_NAME = "role-insert-name"
        const val ROLE_INSERT_DESCRIPTION = "role-insert-description"
        const val ROLE_INSERT_LEVEL = "role-insert-level"
        const val ROLE_INSERT_ACTIVE = "role-insert-active"

        const val ROLE_UPDATE_ID_ROLE_PK = "role-update-id-role-pk"
        const val ROLE_UPDATE_SLUG = "role-update-slug"
        const val ROLE_UPDATE_NAME = "role-update-name"
        const val ROLE_UPDATE_DESCRIPTION = "role-update-description"
        const val ROLE_UPDATE_LEVEL = "role-update-level"
        const val ROLE_UPDATE_ACTIVE = "role-update-active"

        // ROLE_PERMISSION
        const val ROLE_PERMISSION_READ_ID_ROLE_FK = "role-permission-read-id-role-fk"
        const val ROLE_PERMISSION_READ_ID_PERMISSION_FK = "role-permission-read-id-permission-fk"

        const val ROLE_PERMISSION_INSERT_ID_ROLE_FK = "role-permission-insert-id-role-fk"
        const val ROLE_PERMISSION_INSERT_ID_PERMISSION_FK = "role-permission-insert-id-permission-fk"

        const val ROLE_PERMISSION_UPDATE_ID_ROLE_FK = "role-permission-update-id-role-fk"
        const val ROLE_PERMISSION_UPDATE_ID_PERMISSION_FK = "role-permission-update-id-permission-fk"

        // TAG_PRINCIPAL
        const val TAG_PRINCIPAL_READ_ID_TAG_FK = "tag-principal-read-id-tag-fk"
        const val TAG_PRINCIPAL_READ_ID_PRINCIPAL_FK = "tag-principal-read-id-principal-fk"

        const val TAG_PRINCIPAL_INSERT_ID_TAG_FK = "tag-principal-insert-id-tag-fk"
        const val TAG_PRINCIPAL_INSERT_ID_PRINCIPAL_FK = "tag-principal-insert-id-principal-fk"

        const val TAG_PRINCIPAL_UPDATE_ID_TAG_FK = "tag-principal-update-id-tag-fk"
        const val TAG_PRINCIPAL_UPDATE_ID_PRINCIPAL_FK = "tag-principal-update-id-principal-fk"

        // TAG
        const val TAG_READ_ID_TAG_PK = "tag-read-id-tag-pk"
        const val TAG_READ_TITULO = "tag-read-titulo"

        const val TAG_INSERT_ID_TAG_PK = "tag-insert-id-tag-pk"
        const val TAG_INSERT_TITULO = "tag-insert-titulo"

        const val TAG_UPDATE_ID_TAG_PK = "tag-update-id-tag-pk"
        const val TAG_UPDATE_TITULO = "tag-update-titulo"

        // USER
        const val USER_READ_ID_USER_PK = "user-read-id-user-pk"
        const val USER_READ_EMAIL = "user-read-email"
        const val USER_READ_SENHA = "user-read-senha"

        const val USER_INSERT_ID_USER_PK = "user-insert-id-user-pk"
        const val USER_INSERT_EMAIL = "user-insert-email"
        const val USER_INSERT_SENHA = "user-insert-senha"

        const val USER_UPDATE_ID_USER_PK = "user-update-id-user-pk"
        const val USER_UPDATE_EMAIL = "user-update-email"
        const val USER_UPDATE_SENHA = "user-update-senha"

        // USER_PERMISSION
        const val USER_PERMISSION_READ_ID_USER_FK = "user-permission-read-id-user-fk"
        const val USER_PERMISSION_READ_ID_PERMISSION_FK = "user-permission-read-id-permission-fk"

        const val USER_PERMISSION_INSERT_ID_USER_FK = "user-permission-insert-id-user-fk"
        const val USER_PERMISSION_INSERT_ID_PERMISSION_FK = "user-permission-insert-id-permission-fk"

        const val USER_PERMISSION_UPDATE_ID_USER_FK = "user-permission-update-id-user-fk"
        const val USER_PERMISSION_UPDATE_ID_PERMISSION_FK = "user-permission-update-id-permission-fk"

        // USER_ROLE
        const val USER_ROLE_READ_ID_USER_FK = "user-role-read-id-user-fk"
        const val USER_ROLE_READ_ID_ROLE_FK = "user-role-read-id-role-fk"

        const val USER_ROLE_INSERT_ID_USER_FK = "user-role-insert-id-user-fk"
        const val USER_ROLE_INSERT_ID_ROLE_FK = "user-role-insert-id-role-fk"

        const val USER_ROLE_UPDATE_ID_USER_FK = "user-role-update-id-user-fk"
        const val USER_ROLE_UPDATE_ID_ROLE_FK = "user-role-update-id-role-fk"
    }
}