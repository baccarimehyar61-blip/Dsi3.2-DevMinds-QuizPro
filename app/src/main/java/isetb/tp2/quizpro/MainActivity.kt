package isetb.tp2.quizpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import isetb.tp2.quizpro.commercial.DetaiQuizComActivity
import isetb.tp2.quizpro.commercial.PassQuizActivity
import isetb.tp2.quizpro.formateur.DetailQuizActivity
import isetb.tp2.quizpro.formateur.FormateurActivity
import isetb.tp2.quizpro.formateur.ListQuestionActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnConnexion = findViewById<Button>(R.id.btnConnexion)
       // val btnFormateur = findViewById<Button>(R.id.btnFormateur)
        // val btnCommercial = findViewById<Button>(R.id.btnCommercial)

      val  btnOuvrirInscription=findViewById<Button>(R.id.btnOuvrirInscription)
        btnConnexion.setOnClickListener {
            val intent = (Intent(this, LoginActivity::class.java))
            startActivity(intent)

            }
        btnOuvrirInscription.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
            startActivity(intent)
        }

       /* btnFormateur.setOnClickListener {
            val intent = (Intent(this, FormateurActivity::class.java))
            startActivity(intent)
        }

        btnCommercial.setOnClickListener {
            val intent = (Intent(this, PassQuizActivity::class.java))
            startActivity(intent)
        }

*/




    }
}
