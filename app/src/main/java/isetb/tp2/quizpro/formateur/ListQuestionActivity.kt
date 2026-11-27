package isetb.tp2.quizpro.formateur

import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuestionAdapter
import isetb.tp2.quizpro.classes.DbQuestion
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class ListQuestionActivity : AppCompatActivity() {

    private val questions = mutableListOf<DbQuestion>()
    private lateinit var adapter: QuestionAdapter
    private val BASE_URL = "http://10.0.2.2:8081/api/"

    private val instance: QuestionService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewQuestions)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = QuestionAdapter(questions) { question ->
            supprimerQuestion(question.id)
        }
        recyclerView.adapter = adapter

        loadQuestions()
    }

    private fun loadQuestions() {

    }

    private fun supprimerQuestion(idQuest: Int) {
        instance.deleteQuestion(idQuest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@ListQuestionActivity, "Question supprimée", Toast.LENGTH_SHORT).show()
                questions.clear()
                adapter.notifyDataSetChanged()
                loadQuestions()
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ListQuestionActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}