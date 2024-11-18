package br.com.fiap.appaura

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.appaura.databinding.ActivityHomeBinding
import br.com.fiap.appaura.model.EmailDTO
import br.com.fiap.appaura.util.ConfiguracaoUtil
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var apiService: ApiService
    private var token: String? = null
    private lateinit var emailAdapter: EmailAdapter
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConfiguracaoUtil.applyConfiguracao(this)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddEvent.setOnClickListener {
            val intent = Intent(this, AdicionarEventoActivity::class.java)
            startActivity(intent)
        }

        setSupportActionBar(binding.toolbar)

        drawerLayout = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        apiService = RetrofitClient.getApiService()
        token = intent.getStringExtra("TOKEN")
        val userName = intent.getStringExtra("USER_NAME")
        binding.userName = userName

        emailAdapter = EmailAdapter()
        binding.recyclerViewEmails.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = emailAdapter
        }

        binding.fabComposeEmail.setOnClickListener {
            startActivity(Intent(this, EnviarEmailActivity::class.java).apply {
                putExtra("TOKEN", token)
            })
        }

        fetchEmails()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_enviados -> {
                startActivity(Intent(this, EmailsEnviadosActivity::class.java).apply {
                    putExtra("TOKEN", token)
                })
            }

            R.id.nav_configuracoes -> {
                startActivity(Intent(this, ConfiguracaoActivity::class.java).apply {
                    putExtra("TOKEN", token)
                    putExtra("USER_EMAIL", intent.getStringExtra("USER_EMAIL"))
                })
            }

            R.id.nav_logout -> {
                ConfiguracaoUtil.clearConfiguracao(this)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fetchEmails() {
        apiService.listarEmailsRecebidos("Bearer $token")
            .enqueue(object : Callback<List<EmailDTO>> {
                override fun onResponse(
                    call: Call<List<EmailDTO>>,
                    response: Response<List<EmailDTO>>
                ) {
                    if (response.isSuccessful) {
                        val emails =
                            response.body()?.sortedByDescending { it.emailId } ?: emptyList()
                        emailAdapter.submitList(emails)
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            "Falha ao carregar emails",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<EmailDTO>>, t: Throwable) {
                    Toast.makeText(this@HomeActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}