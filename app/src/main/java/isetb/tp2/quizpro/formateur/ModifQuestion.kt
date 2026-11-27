package isetb.tp2.quizpro.formateur

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifQuestionActivity : AppCompatActivity() {
    private val BASE_URL = "http://10.0.2.2:3000/"

    private val instance: QuestionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionService::class.java)
    }

    private var idQuest = -1
    private var idQuiz = -1
    lateinit var edtTypeQuest: EditText
    lateinit var edtContenu: EditText
    lateinit var edtImage: EditText
    lateinit var edtDocument: EditText
    lateinit var edtReponceCorrect: EditText
    lateinit var edtDuree: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_question)

        idQuest = intent.getIntExtra("id", -1)
        edtTypeQuest = findViewById<EditText>(R.id.edtTypeQuest)
        edtContenu = findViewById<EditText>(R.id.edtContenu)
        edtImage = findViewById<EditText>(R.id.edtImage)
        edtDocument = findViewById<EditText>(R.id.edtDocument)
        edtReponceCorrect = findViewById<EditText>(R.id.edtReponceCorrect)
        edtDuree = findViewById<EditText>(R.id.edtDuree)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            modifierQuestion()
        }
        LoadQuestion()
    }

    private fun LoadQuestion() {
        instance.getQuesById(idQuest.toInt()).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                val question = response.body()
                if (question != null) {
                    idQuiz = question.id_quiz

                    edtTypeQuest.setText(question.typeQuest)
                    edtContenu.setText(question.contenu)
                    edtImage.setText(question.image.toString())
                    edtDocument.setText(question.document.toString())
                    edtReponceCorrect.setText(question.reponceCorrect.toString())
                    edtDuree.setText(question.duree.toString())
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun modifierQuestion() {
        val duree = edtDuree.text.toString().toIntOrNull()

        if (duree == null) {
            Toast.makeText(this, "Durée invalide", Toast.LENGTH_SHORT).show()
            return
        }
        val question = Question(
            idQuest = idQuest.toInt(),
            typeQuest = edtTypeQuest.text.toString(),
            contenu = edtContenu.text.toString(),
            image = edtImage.text.toString(),
            document = edtDocument.text.toString(),
            reponceCorrect = edtReponceCorrect.text.toString(),
            duree = duree,
            id_quiz = idQuiz
        )
        instance.updateQuestion(idQuest.toInt(), question).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ModifQuestionActivity, "Question modifiée avec succès", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@ModifQuestionActivity, "Erreur : ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}