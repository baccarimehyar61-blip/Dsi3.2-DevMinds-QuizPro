package isetb.tp2.quizpro.formateur

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailQuizActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:3000/"

    private val instance: QuizService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    private lateinit var tvTitre: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvObjectif: TextView
    private lateinit var tvType: TextView
    private lateinit var tvDuree: TextView
    private lateinit var tvDateLimite: TextView
    private lateinit var tvTentatives: TextView
    private lateinit var tvStatut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_quiz)

        tvTitre = findViewById(R.id.edtTitre)
        tvDescription = findViewById(R.id.edtDesc)
        tvObjectif = findViewById(R.id.edtObjct)
        tvType = findViewById(R.id.edtType)
        tvDuree = findViewById(R.id.edtDuree)
        tvDateLimite = findViewById(R.id.edtLim)
        tvTentatives = findViewById(R.id.edtNbT)
        tvStatut = findViewById(R.id.edtStat)

        val btnRetour = findViewById<Button>(R.id.btnRetourDetail)
        val btnQues= findViewById<Button>(R.id.btnQuestion)
        val idQuiz = intent.getIntExtra("id", -1)

        btnRetour.setOnClickListener {
            val intent = Intent(this, FormateurActivity::class.java)
            startActivity(intent)
        }

        btnQues.setOnClickListener {
            val intent = Intent(this, ListQuestionActivity::class.java)
            intent.putExtra("idQuiz", idQuiz)
            startActivity(intent)
        }

        loadQuiz(idQuiz)

    }

    private fun loadQuiz(idQuiz: Int) {
        instance.getQuizBy(idQuiz).enqueue(object : Callback<Quiz>{
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                val quiz = response.body()

                if (quiz != null) {
                    afficherQuiz(quiz)
                }
                else {
                    Toast.makeText(this@DetailQuizActivity, "Quiz introuvable", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun afficherQuiz(quiz: Quiz) {
        tvTitre.text = quiz.titre
        tvDescription.text = quiz.description
        tvObjectif.text = quiz.objectif
        tvType.text = quiz.typeQuiz
        tvDuree.text = "${quiz.duree} minutes"
        tvDateLimite.text = quiz.dateLimite ?: "Non définie"
        tvTentatives.text = quiz.nbrTentatives.toString()
        tvStatut.text = quiz.statut
    }
}