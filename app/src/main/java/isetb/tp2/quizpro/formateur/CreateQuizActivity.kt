package isetb.tp2.quizpro.formateur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import isetb.tp2.quizpro.R

class CreateQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_quiz)


        val etTitre = findViewById<EditText>(R.id.etTitreQuiz)
        val rgType = findViewById<RadioGroup>(R.id.rgTypeQuiz)
        val etDuree = findViewById<EditText>(R.id.etDureeQuiz)
        val etDateLimite = findViewById<EditText>(R.id.etDateLimiteQuiz)
        val btnAnnuler = findViewById<Button>(R.id.btnAnnulerQuiz)
        val btnValider = findViewById<Button>(R.id.btnValiderQuiz)


        btnAnnuler.setOnClickListener {
            finish()
        }


        btnValider.setOnClickListener {
            val titre = etTitre.text.toString().trim()
            val duree = etDuree.text.toString().trim()
            val dateLimite = etDateLimite.text.toString().trim()


            val selectedTypeId = rgType.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedTypeId)
            val typeQuiz = radioButton?.text?.toString() ?: "QCM"


            if (titre.isEmpty()) {
                etTitre.error = "Le titre est obligatoire"
                Toast.makeText(this, "Veuillez entrer le titre du quiz", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (duree.isEmpty()) {
                etDuree.error = "La durée est obligatoire"
                Toast.makeText(this, "Veuillez entrer la durée", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dateLimite.isEmpty()) {
                etDateLimite.error = "La date limite est obligatoire"
                Toast.makeText(this, "Veuillez entrer la date limite", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Quiz \"$titre\" créé avec succès !", Toast.LENGTH_LONG).show()

            finish()
        }
    }
}