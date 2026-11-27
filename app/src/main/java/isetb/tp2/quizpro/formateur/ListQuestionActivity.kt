package isetb.tp2.quizpro.formateur

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuestFormAdapter
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

    private lateinit var adapter: QuestFormAdapter
    private var idQuiz = -1

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

        idQuiz = intent.getIntExtra("idQuiz", -1)

        val recyclerView = findViewById<RecyclerView>(R.id.ListQues)
        val btnAjouter = findViewById<Button>(R.id.btnAjouterQues)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = QuestFormAdapter(
            ques,
            { idQuest ->
                val intent = Intent(this, ModifQuestionActivity::class.java)
                intent.putExtra("id", idQuest)
                startActivity(intent)
            },
            { idQuest ->
                supprimerQuestion(idQuest)
            }
        )

        recyclerView.adapter = adapter

        btnAjouter.setOnClickListener {
            val intent = Intent(this, AjouterQuesActivity::class.java)
            intent.putExtra("idQuiz", idQuiz)
            startActivity(intent)
        }

        afficherQuestions()
    }

    override fun onResume() {
        super.onResume()
        afficherQuestions()
    }

    private fun afficherQuestions() {

        instance.getQuesByQuiz(idQuiz).enqueue(object : Callback<List<Question>> {

            override fun onResponse(
                call: Call<List<Question>>,
                response: Response<List<Question>>
            ) {
                if (response.isSuccessful) {

                    ques.clear()
                    ques.addAll(response.body() ?: emptyList())

                    adapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(
                        this@ListQuestionActivity,
                        "Erreur : ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Toast.makeText(
                    this@ListQuestionActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun supprimerQuestion(idQuest: Int) {

        instance.deleteQuestion(idQuest).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.isSuccessful) {

                    Toast.makeText(
                        this@ListQuestionActivity,
                        "Question supprimée avec succès",
                        Toast.LENGTH_SHORT
                    ).show()

                    afficherQuestions()

                } else {
                    Toast.makeText(
                        this@ListQuestionActivity,
                        "Erreur suppression : ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(
                    this@ListQuestionActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}