package isetb.tp2.quizpro.commercial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.R

class DetailQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_quiz)

        val titre = intent.getStringExtra("titre")
        val description = intent.getStringExtra("description")
        val objectif = intent.getStringExtra("objectif")
        val duree = intent.getIntExtra("duree", 0)
        val dateLimite = intent.getStringExtra("dateLimite")
        val nbrTentatives = intent.getIntExtra("nbrTentatives", 0)

        findViewById<TextView>(R.id.textTitre).text = titre
        findViewById<TextView>(R.id.textDescription).text = description
        findViewById<TextView>(R.id.textObjectif).text = "Objectif : $objectif"
        findViewById<TextView>(R.id.textDuree).text = "Durée : $duree min"
        findViewById<TextView>(R.id.textDateLimite).text = "Date limite : $dateLimite"
        findViewById<TextView>(R.id.textNbrTentatives).text = "Tentatives autorisées : $nbrTentatives"

        findViewById<Button>(R.id.btnCommencer).setOnClickListener {

            Toast.makeText(this, "Commencer le quiz", Toast.LENGTH_SHORT).show()
        }
    }
}