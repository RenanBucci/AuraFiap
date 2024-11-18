package br.com.fiap.appaura.model

data class ConfiguracaoUsuarioDTO(
    val configuracaoId: Long?,
    val usuarioEmail: String?,
    val temaDark: Boolean,
    val fontSize: Int
)