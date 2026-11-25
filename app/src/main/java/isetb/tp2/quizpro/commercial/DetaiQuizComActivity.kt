package isetb.tp2.quizpro.commercial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetaiQuizComActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:3000/"
    private var QuizEnCour: Quiz? = null
    private var quizId: Int = -1

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
    private lateinit var tvDuree: TextView
    private lateinit var tvNbrTentatives: TextView
    private lateinit var tvTypeStatut: TextView
    private lateinit var btnCommencer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detai_quiz_com)

        tvTitre = findViewById(R.id.Titre)
        tvDescription = findViewById(R.id.Description)
        tvObjectif = findViewById(R.id.Objectif)
        tvDuree = findViewById(R.id.Duree)
        tvNbrTentatives = findViewById(R.id.Tentatives)
        tvTypeStatut = findViewById(R.id.Type)
        btnCommencer = findViewById(R.id.btnCom)

        quizId = intent.getIntExtra("idQuiz", -1)

        if (quizId != -1) {
            QuizDetail(quizId)
        } else {
            Toast.makeText(this, "ID du quiz introuvable", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCommencer.setOnClickListener {
            val q=QuizEnCour
            val intent = Intent(this, PassQuizActivity::class.java)
            if(q != null) {
                intent.putExtra("idQuiz", quizId)
                intent.putExtra("duree", q.duree)
                intent.putExtra("melangeQust", q.melangeQust)
                intent.putExtra("melangeRep", q.melangeRep)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Veuillez patienter, chargement du quiz...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun QuizDetail(quizId: Int) {
        btnCommencer.isEnabled = false

        instance.getQuizBy(quizId).enqueue(object : Callback<Quiz> {
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                    val quiz = response.body()!!
                    QuizEnCour = quiz
                    rechargeQuiz(quiz)
                    btnCommencer.isEnabled = true
            }

            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                tvTitre.text = "Erreur réseau"
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun rechargeQuiz(quiz: Quiz){
        tvTitre.text = quiz.titre
        tvDescription.text = quiz.description
        tvObjectif.text = quiz.objectif
        tvDuree.text = quiz.duree.toString()
        tvNbrTentatives.text =  quiz.nbrTentatives.toString()
        tvTypeStatut.text = ""
    }
}