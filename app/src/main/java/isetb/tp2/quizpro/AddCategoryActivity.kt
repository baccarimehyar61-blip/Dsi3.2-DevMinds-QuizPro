package isetb.tp2.quizpro



import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        etName = findViewById(R.id.etName)
        etDescription = findViewById(R.id.etDescription)
        btnAdd = findViewById(R.id.btnAdd)


        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val description = etDescription.text.toString().trim()


            if (name.isNotEmpty() && description.isNotEmpty()) {
                Toast.makeText(this, "Catégorie ajoutée avec succès (En attente)", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}