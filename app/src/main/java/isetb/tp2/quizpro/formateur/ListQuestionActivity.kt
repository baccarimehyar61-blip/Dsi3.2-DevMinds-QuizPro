package isetb.tp2.quizpro.formateur

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuestionAdapter
import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListQuestionActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:8081/"
    private val questions = mutableListOf<Question>()

    private lateinit var edtQuestion: EditText
    private lateinit var edtChoix1: EditText
    private lateinit var edtChoix2: EditText
    private lateinit var edtChoix3: EditText
    private lateinit var edtChoix4: EditText
    private lateinit var rvQuestions: RecyclerView
    private lateinit var adapter: QuestionAdapter

    private val instance: QuestionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_question)

        edtQuestion = findViewById(R.id.edtQuestion)
        edtChoix1 = findViewById(R.id.edtChoix1)
        edtChoix2 = findViewById(R.id.edtChoix2)
        edtChoix3 = findViewById(R.id.edtChoix3)
        edtChoix4 = findViewById(R.id.edtChoix4)
        rvQuestions = findViewById(R.id.rvQuestions)

        adapter = QuestionAdapter(questions)
        rvQuestions.layoutManager = LinearLayoutManager(this)
        rvQuestions.adapter = adapter

        loadQuestions()
    }

    fun ajouter(v: View) {
        val questionText = edtQuestion.text.toString().trim()
        val choix1 = edtChoix1.text.toString().trim()
        val choix2 = edtChoix2.text.toString().trim()
        val choix3 = edtChoix3.text.toString().trim()
        val choix4 = edtChoix4.text.toString().trim()

        if (questionText.isEmpty()) {
            edtQuestion.error = "Champ obligatoire"
            return
        }

        val question = Question(
            typeQuest = "QCM",
            contenu = questionText,
            image = null,
            document = null,
            reponceCorrect = choix1,
            duree = null
        )

        instance.addQuestion(question).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "Ajouté avec succès", Toast.LENGTH_SHORT).show()
                    edtQuestion.text.clear()
                    edtChoix1.text.clear()
                    edtChoix2.text.clear()
                    edtChoix3.text.clear()
                    edtChoix4.text.clear()
                    loadQuestions()
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadQuestions() {
        instance.getquestion().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful && response.body() != null) {
                    adapter.setQuestions(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}