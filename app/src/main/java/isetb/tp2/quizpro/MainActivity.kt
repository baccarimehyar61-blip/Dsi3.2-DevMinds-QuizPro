package isetb.tp2.quizpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import isetb.tp2.quizpro.admin.AdminActivity
import isetb.tp2.quizpro.commercial.CommercialActivity
import isetb.tp2.quizpro.formateur.FormateurActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnexion = findViewById<Button>(R.id.btnConnexion)
        val btnOuvrirInscription = findViewById<Button>(R.id.btnOuvrirInscription)
        val btnFormateur = findViewById<Button>(R.id.btnFormateur)
        val btnCommercial = findViewById<Button>(R.id.btnCommercial)
        val btnAdmin = findViewById<Button>(R.id.btnAdmin)

        btnConnexion?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnOuvrirInscription?.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
        }

        btnFormateur?.setOnClickListener {
            startActivity(Intent(this, FormateurActivity::class.java))
        }

        btnCommercial?.setOnClickListener {
            startActivity(Intent(this, CommercialActivity::class.java))
        }

        btnAdmin?.setOnClickListener {
            startActivity(Intent(this, AdminActivity::class.java))
        }
    }
}
