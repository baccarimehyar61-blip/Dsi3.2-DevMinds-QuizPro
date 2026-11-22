package isetb.tp2.quizpro

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var btnPause: Button
    private lateinit var tvQuestion: TextView
    private lateinit var rbChoice1: RadioButton
    private lateinit var rbChoice2: RadioButton
    private lateinit var rbChoice3: RadioButton
    private lateinit var rbChoice4: RadioButton
    private lateinit var btnSaveQuestion: Button
    private lateinit var btnPrecedent: Button
    private lateinit var btnPasser: Button

    private lateinit var choiceButtons: List<RadioButton>

    // Exemple de questions w choix (avec ID) venant de la base de données
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

    private var currentQuestionIndex = 0
    private var selectedChoiceId: Int? = null
    private var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        btnPause = findViewById(R.id.btnPause)
        tvQuestion = findViewById(R.id.tvQuestion)
        rbChoice1 = findViewById(R.id.rbChoice1)
        rbChoice2 = findViewById(R.id.rbChoice2)
        rbChoice3 = findViewById(R.id.rbChoice3)
        rbChoice4 = findViewById(R.id.rbChoice4)
        btnSaveQuestion = findViewById(R.id.btnSaveQuestion)
        btnPrecedent = findViewById(R.id.btnPrecedent)
        btnPasser = findViewById(R.id.btnPasser)

        choiceButtons = listOf(rbChoice1, rbChoice2, rbChoice3, rbChoice4)

        bindQuestion(currentQuestionIndex)

        btnPause.setOnClickListener {
            togglePause()
        }

        btnSaveQuestion.setOnClickListener {
            saveSelection()
        }

        btnPrecedent.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                bindQuestion(currentQuestionIndex)
            } else {
                Toast.makeText(this, "Début de la liste", Toast.LENGTH_SHORT).show()
            }
        }

        btnPasser.setOnClickListener {
            nextQuestion()
            Toast.makeText(this, "Question passée", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindQuestion(index: Int) {
        val question = questionsFromDb[index]
        tvQuestion.text = question.questionText
        selectedChoiceId = null

        choiceButtons.forEachIndexed { i, radioButton ->
            if (i < question.choices.size) {
                val choice = question.choices[i]
                radioButton.visibility = RadioButton.VISIBLE
                radioButton.text = choice.text
                radioButton.isChecked = false

                radioButton.setOnClickListener {
                    // Ken ckilika 3la nafs el choix li deja mcochi -> tetna7a
                    if (selectedChoiceId == choice.id) {
                        selectedChoiceId = null
                        updateChoiceChecks(null)
                        Toast.makeText(this, "Choix annulé", Toast.LENGTH_SHORT).show()
                    } else {
                        selectedChoiceId = choice.id
                        updateChoiceChecks(choice.id)
                        Toast.makeText(
                            this,
                            "Choix sélectionné (ID: ${choice.id})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                radioButton.visibility = RadioButton.GONE
                radioButton.isChecked = false
                radioButton.setOnClickListener(null)
            }
        }
    }

    private fun updateChoiceChecks(activeChoiceId: Int?) {
        val currentChoices = questionsFromDb[currentQuestionIndex].choices
        choiceButtons.forEachIndexed { i, radioButton ->
            if (i < currentChoices.size) {
                radioButton.isChecked = (currentChoices[i].id == activeChoiceId)
            }
        }
    }

    private fun saveSelection() {
        val currentQuestion = questionsFromDb[currentQuestionIndex]
        val chosenId = selectedChoiceId

        if (chosenId == null) {
            Toast.makeText(this, "Veuillez sélectionner un choix", Toast.LENGTH_SHORT).show()
            return
        }

        // Hna tnajem tab3eth question.id w chosenId lel API / base de données
        Toast.makeText(
            this,
            "Enregistré -> Question ID: ${currentQuestion.id}, Choix ID: $chosenId",
            Toast.LENGTH_SHORT
        ).show()

        nextQuestion()
    }

    private fun nextQuestion() {
        if (currentQuestionIndex < questionsFromDb.size - 1) {
            currentQuestionIndex++
            bindQuestion(currentQuestionIndex)
        } else {
            Toast.makeText(this, "Fin des questions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun togglePause() {
        isPaused = !isPaused
        val enabledState = !isPaused

        tvQuestion.isEnabled = enabledState
        choiceButtons.forEach { it.isEnabled = enabledState }
        btnSaveQuestion.isEnabled = enabledState
        btnPrecedent.isEnabled = enabledState
        btnPasser.isEnabled = enabledState

        if (isPaused) {
            btnPause.text = "Reprendre"
            Toast.makeText(this, "En pause", Toast.LENGTH_SHORT).show()
        } else {
            btnPause.text = "Pause"
            Toast.makeText(this, "Reprise", Toast.LENGTH_SHORT).show()
        }
    }
}