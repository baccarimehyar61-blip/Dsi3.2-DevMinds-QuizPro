package isetb.tp2.quizpro.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.formateur.FormateurActivity

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val btnGererQuiz = findViewById<Button?>(R.id.btnGererQuiz)
        val btnRetour = findViewById<Button?>(R.id.btnRetourAdmin)

        btnGererQuiz?.setOnClickListener {
            startActivity(Intent(this, FormateurActivity::class.java))
        }

        btnRetour?.setOnClickListener {
            finish()
        }
    }
}
