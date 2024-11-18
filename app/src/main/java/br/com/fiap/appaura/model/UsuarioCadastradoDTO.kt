package br.com.fiap.appaura.model

data class UsuarioCadastroDTO(
    val usuarioId: Long?,
    val nome: String,
    val email: String,
    val senha: String,
)