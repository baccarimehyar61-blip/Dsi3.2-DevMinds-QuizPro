package isetb.tp2.quizpro.commercial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R

class QuizCommercialAdapter(
    private val quizList: List<QuizItem>,
    private val onItemClick: (QuizItem) -> Unit
) : RecyclerView.Adapter<QuizCommercialAdapter.QuizViewHolder>() {

    class QuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView = view.findViewById(R.id.textQuizTitre)
        val dateLimite: TextView = view.findViewById(R.id.textQuizDateLimite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quiz_commercial, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.titre.text = quiz.titre
        holder.dateLimite.text = "Date limite : " + quiz.dateLimite
        holder.itemView.setOnClickListener { onItemClick(quiz) }
    }

    override fun getItemCount(): Int = quizList.size
}