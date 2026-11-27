package isetb.tp2.quizpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Question

class QuestFormAdapter (private val questionList: List<Question>)
    : RecyclerView.Adapter<QuestFormAdapter.QuestionViewHolder>()
{
    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textQuestion: TextView = view.findViewById(R.id.tvItemQuestion)
        val textDetails: TextView = view.findViewById(R.id.tvItemChoices)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questionList[position]
        holder.textQuestion.text = "Question ${position + 1} : ${question.contenu}"
        holder.textDetails.text = "${question.typeQuest} • ${question.duree} secondes"
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}