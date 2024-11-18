package br.com.fiap.appaura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.appaura.databinding.ActivityEmailDetailBinding
import br.com.fiap.appaura.model.EmailDTO

class EmailDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getParcelableExtra<EmailDTO>("EMAIL")
        if (email != null) {
            binding.tvRemetente.text = "De: ${email.remetente}"
            binding.tvDestinatario.text = "Para: ${email.destinatario}"
            binding.tvAssunto.text = "Assunto: ${email.assunto}"
            binding.tvMensagem.text = email.mensagem
            binding.tvMensagem.movementMethod = android.text.method.ScrollingMovementMethod()
        }
    }
}