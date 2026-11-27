package isetb.tp2.quizpro.formateur

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuizFormAdapter
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifQuizActivity : AppCompatActivity() {
    private val BASE_URL = "http://10.0.2.2:3000/"
    private val instance: QuizService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    private var idQuiz = -1
    lateinit var edtTitre : EditText
    lateinit var edtDesc : EditText
    lateinit var edtObjct : EditText
    lateinit var rdType : RadioGroup
    lateinit var edQcm : RadioButton
    lateinit var rdMix : RadioButton
    lateinit var rdVF : RadioButton
    lateinit var edtDuree : EditText
    lateinit var edtLim : EditText
    lateinit var edtNbT : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_quiz)

        idQuiz = intent.getIntExtra("id", -1)
        edtTitre = findViewById<EditText>(R.id.edtTitre)
        edtDesc = findViewById<EditText>(R.id.edtDesc)
        edtObjct = findViewById<EditText>(R.id.edtObjct)
        rdType = findViewById<RadioGroup>(R.id.rdType)
        edQcm = findViewById<RadioButton>(R.id.edQcm)
        rdMix = findViewById<RadioButton>(R.id.rdMix)
        rdVF = findViewById<RadioButton>(R.id.rdVF)
        edtDuree = findViewById<EditText>(R.id.edtDuree)
        edtLim = findViewById<EditText>(R.id.edtLim)
        edtNbT = findViewById<EditText>(R.id.edtNbT)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener{
            modifierQuiz()
        }
        LoadQuiz()
    }

    private fun LoadQuiz(){
        instance.getQuizBy(idQuiz).enqueue(object :  Callback<Quiz>{
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                val quiz=response.body()
                if(quiz != null){
                    edtTitre.setText(quiz.titre)
                    edtDesc.setText(quiz.description)
                    edtObjct.setText(quiz.objectif)
                    edtDuree.setText(quiz.duree.toString())
                    edtLim.setText(quiz.dateLimite.toString())
                    edtNbT.setText(quiz.nbrTentatives.toString())
                }
            }

            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun modifierQuiz(){
        val id = rdType.checkedRadioButtonId
        if (id == -1) {
            Toast.makeText(this, "Choisir le type du Quiz", Toast.LENGTH_SHORT).show()
            return
        }

        val type = findViewById<RadioButton>(id).text.toString()
        val quiz = Quiz(idQuiz = idQuiz, titre =edtTitre.text.toString(), description = edtDesc.text.toString(), objectif = edtObjct.text.toString(),
            duree = edtDuree.text.toString().toInt(),
            dateLimite = edtLim.text.toString(), dateCreation = null, nbrTentatives = edtNbT.text.toString().toInt(),
            melangeQust= false, melangeRep = false, typeQuiz =type, statut = "En_Attent", score = 0f)

        instance.updateQuiz(idQuiz, quiz).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                    Toast.makeText(this@ModifQuizActivity,
                        "Quiz modifié avec succès",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(
                        this@ModifQuizActivity,
                        "Erreur modification : ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        }
        )
    }
}