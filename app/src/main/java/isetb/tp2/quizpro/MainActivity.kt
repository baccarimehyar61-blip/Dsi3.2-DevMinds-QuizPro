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

        val btnFormateur = findViewById<Button>(R.id.btnFormateur)
        val btnCommercial = findViewById<Button>(R.id.btnCommercial)
        val btnAdmin = findViewById<Button>(R.id.btnAdmin)

        btnFormateur.setOnClickListener {
            val intent = Intent(this, FormateurActivity::class.java)
            startActivity(intent)
        }

        btnCommercial.setOnClickListener {
            val intent = Intent(this, CommercialActivity::class.java)
            startActivity(intent)
        }

        btnAdmin.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }
}
