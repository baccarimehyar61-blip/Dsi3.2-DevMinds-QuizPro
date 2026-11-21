package isetb.tp2.quizpro.formateur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.Quiz
import isetb.tp2.quizpro.R
import android.content.Intent

class FormateurActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formateur)

        val btnRetour = findViewById<Button?>(R.id.btnRetourFormateur)
        btnRetour?.setOnClickListener {
            finish()
        }

        val recyclerViewQuiz = findViewById<RecyclerView>(R.id.recyclerViewQuiz)
        recyclerViewQuiz.layoutManager = LinearLayoutManager(this)

        val listeQuiz = listOf(
            Quiz("Techniques de Vente & Prospection", "QCM", "30 min", "2026-12-01", "Actif"),
            Quiz("Négociation Commerciale Avancée", "Mixte", "45 min", "2026-12-05", "Actif"),
            Quiz("Traitement des Objections Clients", "QCM", "20 min", "2026-11-30", "Actif"),
            Quiz("Découverte des Produits Winshot", "Mixte", "40 min", "2026-12-10", "Actif"),
            Quiz("Gestion de la Relation Client (CRM)", "QCM", "25 min", "2026-12-15", "Actif"),
            Quiz("Clôture de Vente (Closing)", "Vrai/Faux", "15 min", "2026-11-28", "Actif"),
            Quiz("Pitch Commercial & Storytelling", "Mixte", "30 min", "2026-12-20", "Brouillon")
        )

        val adapter = QuizAdapter(listeQuiz)
        recyclerViewQuiz.adapter = adapter

        val btnAjouter = findViewById<Button>(R.id.btnAjouterQuiz)
        btnAjouter.setOnClickListener {
            val intent = Intent(this, CreateQuizActivity::class.java)
            startActivity(intent)
        }
    }
}
