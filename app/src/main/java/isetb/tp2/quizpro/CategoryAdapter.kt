package isetb.tp2.quizpro


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val onAcceptClick: (Category) -> Unit,
    private val onRefuseClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtCatName)
        val txtDesc: TextView = itemView.findViewById(R.id.txtCatDesc)
        val btnAccept: Button = itemView.findViewById(R.id.btnAccept)
        val btnRefuse: Button = itemView.findViewById(R.id.btnRefuse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.txtName.text = category.name
        holder.txtDesc.text = category.description

        holder.btnAccept.setOnClickListener {
            onAcceptClick(category)
        }

        holder.btnRefuse.setOnClickListener {
            onRefuseClick(category)
        }
    }

    override fun getItemCount(): Int = categoryList.size
}