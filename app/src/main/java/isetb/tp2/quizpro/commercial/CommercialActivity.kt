package isetb.tp2.quizpro.commercial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.formateur.FormateurActivity

class CommercialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commercial)

        val btnConsulterQuiz = findViewById<Button?>(R.id.btnConsulterQuiz)
        val btnRetour = findViewById<Button?>(R.id.btnRetourCommercial)

        btnConsulterQuiz?.setOnClickListener {
            startActivity(Intent(this, FormateurActivity::class.java))
        }

        btnRetour?.setOnClickListener {
            finish()
        }
    }
}
