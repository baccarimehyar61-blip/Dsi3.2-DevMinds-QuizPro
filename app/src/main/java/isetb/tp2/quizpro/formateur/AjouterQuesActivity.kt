package isetb.tp2.quizpro.formateur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AjouterQuesActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:3000/"
    private val instance: QuestionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_question)
        val idQuiz = intent.getIntExtra("idQuiz", -1)

        val etType = findViewById<EditText>(R.id.etTypeQuest)
        val etContenu = findViewById<EditText>(R.id.etContenu)
        val etReponse = findViewById<EditText>(R.id.etReponse)
        val etDuree = findViewById<EditText>(R.id.etDuree)
        val etImage = findViewById<EditText>(R.id.etImage)
        val etDocument = findViewById<EditText>(R.id.etDocument)

        val btnAjouter = findViewById<Button>(R.id.btnEnregistrerQues)

        btnAjouter.setOnClickListener {
            val type = etType.text.toString()
            val contenu = etContenu.text.toString()
            val reponse = etReponse.text.toString()
            val duree = etDuree.text.toString().toIntOrNull()
            val image = etImage.text.toString()
            val document = etDocument.text.toString()

            if (type.isEmpty() || contenu.isEmpty() ||
                reponse.isEmpty() || duree == null || duree <= 0
            ) {
                Toast.makeText(this, "Remplir les champs obligatoires", Toast.LENGTH_SHORT).show()
            } else if (idQuiz == -1) {
                Toast.makeText(this, "Quiz introuvable", Toast.LENGTH_SHORT).show()
            } else {
                val question = Question(
                    typeQuest = type,
                    contenu = contenu,
                    image = image.ifEmpty { null },
                    document = document.ifEmpty { null },
                    reponceCorrect = reponse,
                    duree = duree,
                    id_quiz = idQuiz
                )
                AjouterQues(question)
            }
        }
    }

    private fun AjouterQues(question : Question){
        instance.addQuestion(question).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AjouterQuesActivity, "Question ajoutée avec succès !", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AjouterQuesActivity, "Erreur serveur : ${response.code()} - ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Question>, t: Throwable) {
                Toast.makeText(this@AjouterQuesActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
