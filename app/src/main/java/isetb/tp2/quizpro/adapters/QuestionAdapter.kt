package isetb.tp2.quizpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.DbQuestion

class QuestionAdapter(
    private val items: MutableList<DbQuestion>,
    private val onDeleteClick: (DbQuestion) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvItemQuestion)
        val tvChoices: TextView = itemView.findViewById(R.id.tvItemChoices)
        val btnSupprimer: Button = itemView.findViewById(R.id.btnSupprimer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = items[position]
        holder.tvQuestion.text = "Question ${position + 1} : ${question.questionText}"
        holder.tvChoices.text = question.choices.joinToString(" | ") { it.text }

        holder.btnSupprimer.setOnClickListener {
            onDeleteClick(question)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setQuestions(newList: List<DbQuestion>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}