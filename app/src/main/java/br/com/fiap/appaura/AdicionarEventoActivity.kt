package br.com.fiap.appaura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityAdicionarEventoBinding
import br.com.fiap.appaura.dto.EventoDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdicionarEventoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarEventoBinding
    private lateinit var apiService: ApiService
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.getApiService()
        token = intent.getStringExtra("TOKEN")

        binding.btnSalvarEvento.setOnClickListener {
            val nomeEvento = binding.etNomeEvento.text.toString()
            val descricao = binding.etDescricaoEvento.text.toString()
            val dataHora = binding.calendarView.date.toString() // Ajuste conforme necessário

            val eventoDTO = EventoDTO(null, nomeEvento, descricao, dataHora)
            apiService.adicionarEvento("Bearer $token", eventoDTO).enqueue(object : Callback<EventoDTO> {
                override fun onResponse(call: Call<EventoDTO>, response: Response<EventoDTO>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AdicionarEventoActivity, "Evento adicionado com sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AdicionarEventoActivity, "Falha ao adicionar evento", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EventoDTO>, t: Throwable) {
                    Toast.makeText(this@AdicionarEventoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}