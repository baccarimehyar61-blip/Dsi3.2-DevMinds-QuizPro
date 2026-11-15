package isetb.tp2.quizpro

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category) // ربطها بواجهة activity_category.xml

        // ربط الـ RecyclerView (تأكدي من إضافة id/recyclerView في ملف الواجهة XML)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dummyList = listOf(
            Category(1, "Intelligence Artificielle", "Machine learning et Deep learning", "pending"),
            Category(2, "Sécurité Informatique", "Ethical hacking et réseaux", "pending")
        )

        adapter = CategoryAdapter(
            categoryList = dummyList,
            onAcceptClick = { category ->
                Toast.makeText(this, "Accepté: ${category.name}", Toast.LENGTH_SHORT).show()
            },
            onRefuseClick = { category ->
                Toast.makeText(this, "Refusé: ${category.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter
    }
}