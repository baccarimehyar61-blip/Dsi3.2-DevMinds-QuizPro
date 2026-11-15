package isetb.tp2.quizpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnConnexion = findViewById<Button>(R.id.btnConnexion)

        btnConnexion.setOnClickListener {
            val intent = (Intent(this, LoginActivity::class.java))
            startActivity(intent)
            findViewById<Button>(R.id.btnOuvrirInscription).setOnClickListener {
                startActivity(Intent(this, InscriptionActivity::class.java))

            }
        }
        val btnShowCategories = findViewById<Button>(R.id.btnShowCategories)
        btnShowCategories.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
         }
        val btnAddCategories = findViewById<Button>(R.id.btnAddCategories)
        btnAddCategories.setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }







    }
}
