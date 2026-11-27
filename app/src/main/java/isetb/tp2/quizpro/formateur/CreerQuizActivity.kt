package isetb.tp2.quizpro.formateur

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreerQuizActivity : AppCompatActivity() {
    private val quizs = mutableListOf<Quiz>()
    private val BASE_URL = "http://10.0.2.2:3000/"

    private val instance: QuizService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_quiz)

        val etTitre = findViewById<EditText>(R.id.etTitreQuiz)
        val rgType = findViewById<RadioGroup>(R.id.rgTypeQuiz)
        val etDuree = findViewById<EditText>(R.id.etDureeQuiz)
        val etDateLimite = findViewById<EditText>(R.id.etDateLimiteQuiz)
        val etObject =findViewById<EditText>(R.id.etObjectQuiz)
        val etDesc =findViewById<EditText>(R.id.etDescQuiz)
        val btnAnnuler = findViewById<Button>(R.id.btnAnnulerQuiz)
        val btnValider = findViewById<Button>(R.id.btnValiderQuiz)

        btnAnnuler.setOnClickListener{
            val intent = Intent(this, FormateurActivity::class.java)
            startActivity(intent)
        }

        btnValider.setOnClickListener{
            val titre = etTitre.text.toString().trim()
            val description = etDesc.text.toString().trim()
            val objectif = etObject.text.toString().trim()
            val dureeText = etDuree.text.toString().trim()
            val dateLimite = etDateLimite.text.toString().trim()

            if (titre.isEmpty()) {
                etTitre.error = "Le titre est obligatoire"
                return@setOnClickListener
            }

            if (dureeText.isEmpty()) {
                etDuree.error = "La durée est obligatoire"
                return@setOnClickListener
            }

            if (dateLimite.isEmpty()) {
                etDateLimite.error = "La date limite est obligatoire"
                return@setOnClickListener
            }
            val duree = dureeText.toIntOrNull()

            if (duree == null || duree <= 0) {
                etDuree.error = "Veuillez saisir une durée valide"
                return@setOnClickListener
            }

            val selectedTypeId = rgType.checkedRadioButtonId
            val rb = findViewById<RadioButton>(selectedTypeId)
            val typeQuiz = rb?.text?.toString() ?: "QCM"
            val quiz = Quiz(titre = titre, description = description, objectif = objectif, duree = duree, dateLimite = dateLimite, dateCreation = null, nbrTentatives = 1, melangeQust = false, melangeRep = false, typeQuiz = typeQuiz, statut = "En_Attent", score = null)
            ajouterQuiz(quiz)
        }
        val btnValiderQuiz =findViewById<Button>(R.id.btnValiderQuiz)
        btnValiderQuiz.setOnClickListener{
            startActivity(Intent(this, ListQuestionActivity::class.java))
        }

    }

    private fun ajouterQuiz(quiz :Quiz){
        instance.addQuiz(quiz).enqueue(object : Callback<Quiz>{
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                Toast.makeText(this@CreerQuizActivity, "Quiz créé avec succès !", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}