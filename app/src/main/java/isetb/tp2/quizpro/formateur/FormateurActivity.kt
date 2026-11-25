package isetb.tp2.quizpro.formateur

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.MainActivity
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.adapters.QuizFormAdapter
import isetb.tp2.quizpro.classes.Quiz
import isetb.tp2.quizpro.services.QuizService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FormateurActivity : AppCompatActivity() {

    private val quizs = mutableListOf<Quiz>()
    private val BASE_URL = "http://10.0.2.2:3000/"
    private lateinit var adapter : QuizFormAdapter

    private val instance: QuizService by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(QuizService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formateur)

        val btnRetour = findViewById<Button?>(R.id.btnRetourFormateur)
        val btnAjout =findViewById<Button>(R.id.btnAjouterQuiz)
        val btnModif = findViewById<Button>(R.id.btnModif)
        btnRetour.setOnClickListener{
            val intentR =Intent(this, MainActivity::class.java)
            startActivity(intentR)
        }

        btnAjout.setOnClickListener{
            val intentA =Intent(this, CreerQuizActivity::class.java)
            startActivity(intentA)
        }

        btnModif.setOnClickListener{
            val intentM = Intent(this, ModifQuizActivity::class.java)
            startActivity(intentM)
        }

        val recyclerViewQuiz = findViewById<RecyclerView>(R.id.recyclerViewQuiz)
        recyclerViewQuiz.layoutManager = LinearLayoutManager(this)

        adapter = QuizFormAdapter (quizs,{idQuiz ->
            val intent = Intent(this, DetailQuizActivity::class.java)
            intent.putExtra("id", idQuiz)
            startActivity(intent)
        },
            {idQuiz -> supprimerQuiz (idQuiz) },
            {idQuiz, quiz ->
                val intent =Intent(this, ModifQuizActivity::class.java)
                intent.putExtra("id", idQuiz)
                startActivity(intent)
            }
        )

        recyclerViewQuiz.adapter = adapter
        loadStudents()

    }

    private fun loadStudents(){
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

    private fun supprimerQuiz(idQuiz : Long){
        instance.deleteQuiz(idQuiz).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@FormateurActivity, "Quiz supprimé", Toast.LENGTH_SHORT).show()
                quizs.clear()
                adapter.notifyDataSetChanged()
                loadStudents()
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(getBaseContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }




}
