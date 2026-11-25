package isetb.tp2.quizpro.commercial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.ChoiceItem
import isetb.tp2.quizpro.classes.DbQuestion
import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.services.QuestionService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PassQuizActivity : AppCompatActivity() {
    private val questionsFromDb: List<DbQuestion> = listOf(
        DbQuestion(
            id = 101,
            questionText = "Quelle est la première étape d'un entretien commercial ?",
            choices = listOf(
                ChoiceItem(id = 1, text = "Prise de contact et découverte"),
                ChoiceItem(id = 2, text = "Négociation du prix direct"),
                ChoiceItem(id = 3, text = "Envoi de la facture"),
                ChoiceItem(id = 4, text = "Clôture sans présentation")
            )
        ),
        DbQuestion(
            id = 102,
            questionText = "Comment gérer une objection sur le prix ?",
            choices = listOf(
                ChoiceItem(id = 5, text = "Couper la parole au client"),
                ChoiceItem(id = 6, text = "Reformuler et mettre en avant la valeur"),
                ChoiceItem(id = 7, text = "Annuler l'offre immédiatement"),
                ChoiceItem(id = 8, text = "Ignorer la remarque")
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_quiz)
    }
}