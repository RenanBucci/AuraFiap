package br.com.fiap.appaura

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityLoginBinding
import br.com.fiap.appaura.model.ConfiguracaoUsuarioDTO
import br.com.fiap.appaura.model.LoginDTO
import br.com.fiap.appaura.model.TokenDTO
import br.com.fiap.appaura.util.ConfiguracaoUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            val loginDTO = LoginDTO(email, senha)
            apiService.login(loginDTO).enqueue(object : Callback<TokenDTO> {
                override fun onResponse(call: Call<TokenDTO>, response: Response<TokenDTO>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.putExtra("TOKEN", token)
                        intent.putExtra("USER_EMAIL", email)
                        startActivity(intent)
                        finish()

                        apiService.obterConfiguracao("Bearer $token", email).enqueue(object : Callback<ConfiguracaoUsuarioDTO> {
                            override fun onResponse(call: Call<ConfiguracaoUsuarioDTO>, response: Response<ConfiguracaoUsuarioDTO>) {
                                if (response.isSuccessful) {
                                    val configuracao = response.body()
                                    if (configuracao != null) {
                                        ConfiguracaoUtil.saveConfiguracao(this@LoginActivity, configuracao.temaDark, configuracao.fontSize)
                                        ConfiguracaoUtil.applyConfiguracao(this@LoginActivity)
                                    }
                                }
                            }

                            override fun onFailure(call: Call<ConfiguracaoUsuarioDTO>, t: Throwable) {
                                Toast.makeText(this@LoginActivity, "Erro ao obter configurações: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<TokenDTO>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}