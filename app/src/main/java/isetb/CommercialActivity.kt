package isetb.tp2.quizpro.commercial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R

class CommercialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commercial)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewQuiz)

        val quizList = listOf(
            QuizItem(1, "Quiz Produits", "Test sur les produits", "Evaluer les connaissances",
                30, "2026-12-31", "2026-11-01", 2),
            QuizItem(2, "Techniques de vente", "Evaluation des techniques", "Maîtriser la vente",
                45, "2026-12-15", "2026-11-02", 1),
            QuizItem(3, "Relation client", "Quiz d'entraînement", "Améliorer l'accueil",
                20, "2026-11-30", "2026-11-03", 3),
            QuizItem(4, "Services", "Test sur les services", "Connaître l'offre de services",
                25, "2026-12-20", "2026-11-04", 2),
            QuizItem(5, "Négociation", "Quiz sur la négociation", "Conclure une vente",
                35, "2026-12-25", "2026-11-05", 1),
            QuizItem(6, "Fidélisation client", "Quiz sur la fidélisation", "Garder les clients",
                30, "2026-12-28", "2026-11-06", 2),
            QuizItem(7, "Prospection", "Techniques de prospection", "Trouver de nouveaux clients",
                40, "2026-12-18", "2026-11-07", 1),
            QuizItem(8, "Argumentaire", "Construire un argumentaire", "Convaincre efficacement",
                25, "2026-12-22", "2026-11-08", 3)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = QuizCommercialAdapter(quizList) { quiz ->
            val intent = Intent(this, DetailQuizActivity::class.java)
            intent.putExtra("titre", quiz.titre)
            intent.putExtra("description", quiz.description)
            intent.putExtra("objectif", quiz.objectif)
            intent.putExtra("duree", quiz.duree)
            intent.putExtra("dateLimite", quiz.dateLimite)
            intent.putExtra("nbrTentatives", quiz.nbrTentatives)
            startActivity(intent)
        }
    }
}