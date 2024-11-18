package br.com.fiap.appaura.dto

data class EventoDTO(
    val eventoId: Long?,
    val nomeEvento: String,
    val descricao: String,
    val dataHoraEvento: String
)