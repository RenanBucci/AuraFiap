package br.com.fiap.appaura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.appaura.databinding.ActivityEmailsEnviadosBinding
import br.com.fiap.appaura.model.EmailDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailsEnviadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailsEnviadosBinding
    private lateinit var apiService: ApiService
    private var token: String? = null
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailsEnviadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()
        token = intent.getStringExtra("TOKEN")

        emailAdapter = EmailAdapter(isSentEmails = true)
        binding.rvEmailsEnviados.layoutManager = LinearLayoutManager(this)
        binding.rvEmailsEnviados.adapter = emailAdapter

        carregarEmailsEnviados()
    }

    private fun carregarEmailsEnviados() {
        apiService.listarEmailsEnviados("Bearer $token").enqueue(object : Callback<List<EmailDTO>> {
            override fun onResponse(call: Call<List<EmailDTO>>, response: Response<List<EmailDTO>>) {
                if (response.isSuccessful) {
                    val emails = response.body()?.reversed() ?: emptyList()
                    emailAdapter.submitList(emails)
                } else {
                    Toast.makeText(this@EmailsEnviadosActivity, "Falha ao carregar emails", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<EmailDTO>>, t: Throwable) {
                Toast.makeText(this@EmailsEnviadosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}