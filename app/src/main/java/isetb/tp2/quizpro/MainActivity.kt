package isetb.tp2.quizpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import isetb.tp2.quizpro.formateur.CreerQuizActivity

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

        val btncrerQuiz =findViewById<Button>(R.id.btncrerQuiz)
        btncrerQuiz.setOnClickListener{
            startActivity(Intent(this, CreerQuizActivity::class.java))
        }




    }
}
