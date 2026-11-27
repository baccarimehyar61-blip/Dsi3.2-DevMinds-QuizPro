package isetb.tp2.quizpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Question

class QuestionAdapter(
    private val items: MutableList<Question>
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvQuestion: TextView = view.findViewById(R.id.tvItemQuestion)
        val tvChoices: TextView = view.findViewById(R.id.tvItemChoices)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_ques, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = items[position]
        holder.tvQuestion.text = "Question ${position + 1} : $question"
        holder.tvChoices.text = ""

    }

    override fun getItemCount(): Int = items.size

    fun setQuestions(newList: List<Question>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}