package isetb.tp2.quizpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import isetb.tp2.quizpro.admin.AdminActivity
import isetb.tp2.quizpro.commercial.CommercialActivity
import isetb.tp2.quizpro.formateur.FormateurActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val textInscrire = findViewById<TextView?>(R.id.textInscrire)

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Entrez votre email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Entrez votre mot de passe", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show()
                val intent = when {
                    email.contains("admin", ignoreCase = true) -> Intent(this, AdminActivity::class.java)
                    email.contains("formateur", ignoreCase = true) -> Intent(this, FormateurActivity::class.java)
                    else -> Intent(this, CommercialActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
        }

        textInscrire?.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
        }
    }
}
