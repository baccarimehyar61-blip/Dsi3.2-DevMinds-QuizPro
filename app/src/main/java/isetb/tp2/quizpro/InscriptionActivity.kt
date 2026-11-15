package isetb.tp2.quizpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.commercial.CommercialActivity
import isetb.tp2.quizpro.formateur.FormateurActivity

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        val etNom = findViewById<EditText>(R.id.etNom)
        val etPrenom = findViewById<EditText>(R.id.etPrenom)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelephone = findViewById<EditText>(R.id.etTelephone)
        val etMotDePasse = findViewById<EditText>(R.id.etMotDePasse)
        val etConfirmation = findViewById<EditText>(R.id.etConfirmation)
        val rbFormateur = findViewById<RadioButton>(R.id.rbFormateur)
        val btnInscrire = findViewById<Button>(R.id.btnInscrire)
        val btnAnnuler = findViewById<Button>(R.id.btnAnnuler)

        btnInscrire.setOnClickListener {
            val nom = etNom.text.toString().trim()
            val prenom = etPrenom.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val telephone = etTelephone.text.toString().trim()
            val motDePasse = etMotDePasse.text.toString()
            val confirmation = etConfirmation.text.toString()

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() ||
                telephone.isEmpty() || motDePasse.isEmpty() || confirmation.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else if (!email.contains("@") || !email.contains(".")) {
                Toast.makeText(this, "Adresse email invalide", Toast.LENGTH_SHORT).show()
            } else if (telephone.length != 8) {
                Toast.makeText(this, "Le téléphone doit contenir 8 chiffres", Toast.LENGTH_SHORT).show()
            } else if (motDePasse.length < 6) {
                Toast.makeText(this, "Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show()
            } else if (motDePasse != confirmation) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
            } else {
                val isFormateur = rbFormateur.isChecked
                val role = if (isFormateur) "Formateur" else "Commercial"

                AlertDialog.Builder(this)
                    .setTitle("Inscription réussie")
                    .setMessage("Bienvenue $prenom $nom !\nRôle : $role\nEmail : $email")
                    .setPositiveButton("OK") { _, _ ->
                        val intent = if (isFormateur) {
                            Intent(this, FormateurActivity::class.java)
                        } else {
                            Intent(this, CommercialActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    }
                    .show()
            }
        }

        btnAnnuler.setOnClickListener {
            finish()
        }
    }
}
