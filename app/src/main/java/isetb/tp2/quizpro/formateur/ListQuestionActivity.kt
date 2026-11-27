package isetb.tp2.quizpro.formateur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuesFormAdapter
import isetb.tp2.quizpro.adapters.QuestionComAdapter
import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListQuestionActivity : AppCompatActivity() {

    private val ques = mutableListOf<Question>()
    private val BASE_URL = "http://10.0.2.2:3000/"
    private lateinit var adapter: QuesFormAdapter
    private var idQuiz = -1

    private val instance: QuestionService by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(QuestionService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_question)
        idQuiz = intent.getIntExtra("idQuiz", -1)

        val btnAjouter = findViewById<Button>(R.id.btnAjouterQues)

        btnAjouter.setOnClickListener {
            val intent = Intent(this, AjouterQuesActivity::class.java)
            intent.putExtra("idQuiz", idQuiz)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.ListQues)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = QuesFormAdapter(ques)
        recyclerView.adapter = adapter

        if (idQuiz != -1) {
            afficherQuestions()
        }
    }

    private fun afficherQuestions() {
        instance.getquestions().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                val liste = response.body()
                ques.clear()
                val questionsQuiz = mutableListOf<Question>()

                if (liste != null) {
                    for (question in liste) {
                        if (question.id_quiz == idQuiz) {
                            questionsQuiz.add(question)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Toast.makeText(getBaseContext(),t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}