package isetb.tp2.quizpro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var tvTitreQuiz: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvPourcentage: TextView
    private lateinit var tvMention: TextView
    private lateinit var btnRetour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvTitreQuiz = findViewById(R.id.tvTitreQuiz)
        tvScore = findViewById(R.id.tvScore)
        tvPourcentage = findViewById(R.id.tvPourcentage)
        tvMention = findViewById(R.id.tvMention)
        btnRetour = findViewById(R.id.btnRetour)

        val titre = intent.getStringExtra("QUIZ_TITRE") ?: "Quiz"
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)

        tvTitreQuiz.text = titre
        tvScore.text = "$score / $total"

        if (total > 0) {
            val pourcentage = (score * 100) / total
            tvPourcentage.text = "$pourcentage %"
            tvMention.text = if (pourcentage >= 50) "Validé" else "À refaire"
        } else {
            tvPourcentage.text = "0 %"
            tvMention.text = ""
        }

        btnRetour.setOnClickListener {
            finish()
        }
    }
}