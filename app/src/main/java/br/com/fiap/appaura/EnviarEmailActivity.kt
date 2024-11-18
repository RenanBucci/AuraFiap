package br.com.fiap.appaura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityEnviarEmailBinding
import br.com.fiap.appaura.model.EmailDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnviarEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviarEmailBinding
    private lateinit var apiService: ApiService
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviarEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()
        token = intent.getStringExtra("TOKEN")

        binding.btnEnviar.setOnClickListener {
            val destinatario = binding.etDestinatario.text.toString()
            val assunto = binding.etAssunto.text.toString()
            val mensagem = binding.etMensagem.text.toString()

            val emailDTO = EmailDTO(null, null, destinatario, assunto, mensagem)
            apiService.enviarEmail("Bearer $token", emailDTO).enqueue(object : Callback<EmailDTO> {
                override fun onResponse(call: Call<EmailDTO>, response: Response<EmailDTO>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@EnviarEmailActivity, "Email enviado com sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@EnviarEmailActivity, "Falha ao enviar email", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EmailDTO>, t: Throwable) {
                    Toast.makeText(this@EnviarEmailActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}