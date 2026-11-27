package isetb.tp2.quizpro.commercial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuizFormAdapter
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListQuizComActivity : AppCompatActivity() {

    private val quizs = mutableListOf<Quiz>()
    private val BASE_URL = "http://10.0.2.2:3000/"
    private lateinit var adapter: QuizFormAdapter

    private val instance: QuizService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_quiz_com)

        val recyclerViewQuiz = findViewById<RecyclerView>(R.id.listQuiz)
        recyclerViewQuiz.layoutManager = LinearLayoutManager(this)

        adapter = QuizFormAdapter(quizs, { idQuiz ->
            val intent = Intent(this, DetaiQuizComActivity::class.java)
            intent.putExtra("idQuiz", idQuiz)
            startActivity(intent)
        }, { idQuiz -> }, { idQuiz, quiz -> })
        recyclerViewQuiz.adapter = adapter

        loadQuizs()
    }

    private fun loadQuizs() {
        instance.getQuizs().enqueue(object : Callback<List<Quiz>> {
            override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
                    quizs.clear()
                    quizs.addAll(response.body() ?: emptyList())
                    adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}