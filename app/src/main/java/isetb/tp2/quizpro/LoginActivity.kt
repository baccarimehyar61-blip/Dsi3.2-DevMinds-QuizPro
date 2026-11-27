package isetb.tp2.quizpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.classes.Utilisateur
import isetb.tp2.quizpro.commercial.ListQuizComActivity
import isetb.tp2.quizpro.formateur.CreerQuizActivity
import isetb.tp2.quizpro.formateur.FormateurActivity
import isetb.tp2.quizpro.services.utilisateur
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:3000/"

    private val instance: utilisateur by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(utilisateur::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val textInscrire = findViewById<TextView>(R.id.textInscrire)

        textInscrire.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Entrez votre email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Entrez votre mot de passe", Toast.LENGTH_SHORT).show()
            } else {
                instance.getUtilisateurs().enqueue(object : Callback<List<Utilisateur>> {
                    override fun onResponse(
                        call: Call<List<Utilisateur>>,
                        response: Response<List<Utilisateur>>
                    ) {
                        if (response.isSuccessful) {
                            val user = response.body()?.find {
                                it.email.equals(email, ignoreCase = true) &&
                                        it.motdepasse == password
                            }

                            if (user != null) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Connexion réussie",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val isFormateur = user.role.equals("Formateur", ignoreCase = true)
                                val nextActivity = if (isFormateur) {
                                    FormateurActivity::class.java
                                } else {
                                    ListQuizComActivity::class.java
                                }

                                val intent = Intent(this@LoginActivity, nextActivity)
                                if (isFormateur) {
                                    intent.putExtra("idFormateur", user.id)
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Email ou mot de passe incorrect",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Erreur connexion (${response.code()})",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<List<Utilisateur>>, t: Throwable) {
                        Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}