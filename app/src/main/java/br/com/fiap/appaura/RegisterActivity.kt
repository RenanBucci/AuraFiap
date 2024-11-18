package br.com.fiap.appaura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityRegisterBinding
import br.com.fiap.appaura.model.UsuarioCadastroDTO
import br.com.fiap.appaura.model.UsuarioExibicaoDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()

        binding.btnRegister.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            val usuarioCadastroDTO = UsuarioCadastroDTO(null, nome, email, senha)
            apiService.register(usuarioCadastroDTO).enqueue(object : Callback<UsuarioExibicaoDTO> {
                override fun onResponse(call: Call<UsuarioExibicaoDTO>, response: Response<UsuarioExibicaoDTO>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UsuarioExibicaoDTO>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}