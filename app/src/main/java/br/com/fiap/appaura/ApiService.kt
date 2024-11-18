package br.com.fiap.appaura

import br.com.fiap.appaura.dto.EventoDTO
import br.com.fiap.appaura.model.ConfiguracaoUsuarioDTO
import br.com.fiap.appaura.model.EmailDTO
import br.com.fiap.appaura.model.LoginDTO
import br.com.fiap.appaura.model.TokenDTO
import br.com.fiap.appaura.model.UsuarioCadastroDTO
import br.com.fiap.appaura.model.UsuarioExibicaoDTO
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("/auth/login")
    fun login(@Body loginDTO: LoginDTO): Call<TokenDTO>

    @POST("/auth/register")
    fun register(@Body usuarioCadastroDTO: UsuarioCadastroDTO): Call<UsuarioExibicaoDTO>

    @POST("/email/enviar")
    fun enviarEmail(@Header("Authorization") token: String, @Body emailDTO: EmailDTO): Call<EmailDTO>

    @GET("/email/recebidos")
    fun listarEmailsRecebidos(@Header("Authorization") token: String): Call<List<EmailDTO>>

    @GET("/email/enviados")
    fun listarEmailsEnviados(@Header("Authorization") token: String): Call<List<EmailDTO>>

    @GET("/configuracao/{email}")
    fun obterConfiguracao(@Header("Authorization") token: String, @Path("email") email: String): Call<ConfiguracaoUsuarioDTO>

    @PUT("/configuracao")
    fun atualizarConfiguracao(@Header("Authorization") token: String, @Body configuracaoDTO: ConfiguracaoUsuarioDTO): Call<ConfiguracaoUsuarioDTO>

    @POST("/eventos")
    fun adicionarEvento(@Header("Authorization") token: String, @Body evento: EventoDTO): Call<EventoDTO>
}