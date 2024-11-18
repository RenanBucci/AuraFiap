package br.com.fiap.appaura

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.appaura.model.EmailDTO

class EmailAdapter(private val isSentEmails: Boolean = false) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    private var emailList: List<EmailDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        if (isSentEmails) {
            holder.tvRemetente.text = email.destinatario
        } else {
            holder.tvRemetente.text = email.remetente
        }
        holder.tvAssunto.text = email.assunto
        holder.tvMensagem.text = email.mensagem

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EmailDetailActivity::class.java)
            intent.putExtra("EMAIL", email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = emailList.size

    fun submitList(list: List<EmailDTO>) {
        emailList = list
        notifyDataSetChanged()
    }

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRemetente: TextView = itemView.findViewById(R.id.tvRemetente)
        val tvAssunto: TextView = itemView.findViewById(R.id.tvAssunto)
        val tvMensagem: TextView = itemView.findViewById(R.id.tvMensagem)
    }
}