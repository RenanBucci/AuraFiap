// ConfiguracaoActivity.kt
package br.com.fiap.appaura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityConfiguracaoBinding
import br.com.fiap.appaura.model.ConfiguracaoUsuarioDTO
import br.com.fiap.appaura.util.ConfiguracaoUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfiguracaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracaoBinding
    private lateinit var apiService: ApiService
    private var token: String? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()
        token = intent.getStringExtra("TOKEN")
        email = intent.getStringExtra("USER_EMAIL")

        carregarConfiguracao(email)

        binding.btnSalvar.setOnClickListener {
            val temaDark = binding.switchTemaDark.isChecked
            val fontSize = binding.etFontSize.text.toString().toInt()
            val configuracaoDTO = ConfiguracaoUsuarioDTO(null, email, temaDark, fontSize)
            apiService.atualizarConfiguracao("Bearer $token", configuracaoDTO).enqueue(object : Callback<ConfiguracaoUsuarioDTO> {
                override fun onResponse(call: Call<ConfiguracaoUsuarioDTO>, response: Response<ConfiguracaoUsuarioDTO>) {
                    if (response.isSuccessful) {
                        ConfiguracaoUtil.saveConfiguracao(this@ConfiguracaoActivity, temaDark, fontSize)
                        ConfiguracaoUtil.applyConfiguracao(this@ConfiguracaoActivity)
                        Toast.makeText(this@ConfiguracaoActivity, "Configuração salva com sucesso", Toast.LENGTH_SHORT).show()
                        restartActivity()
                    } else {
                        Toast.makeText(this@ConfiguracaoActivity, "Falha ao salvar configuração", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ConfiguracaoUsuarioDTO>, t: Throwable) {
                    Toast.makeText(this@ConfiguracaoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun carregarConfiguracao(email: String?) {
        if (email != null) {
            apiService.obterConfiguracao("Bearer $token", email).enqueue(object : Callback<ConfiguracaoUsuarioDTO> {
                override fun onResponse(call: Call<ConfiguracaoUsuarioDTO>, response: Response<ConfiguracaoUsuarioDTO>) {
                    if (response.isSuccessful) {
                        val configuracao = response.body()
                        if (configuracao != null) {
                            binding.switchTemaDark.isChecked = configuracao.temaDark
                            binding.etFontSize.setText(configuracao.fontSize.toString())
                        }
                    } else {
                        Toast.makeText(this@ConfiguracaoActivity, "Falha ao carregar configuração", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ConfiguracaoUsuarioDTO>, t: Throwable) {
                    Toast.makeText(this@ConfiguracaoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun restartActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}