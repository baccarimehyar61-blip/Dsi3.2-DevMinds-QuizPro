package isetb.tp2.quizpro.formateur

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.Quiz
import isetb.tp2.quizpro.R

class QuizAdapter(
    private val quizList: List<Quiz>
) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    class QuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitre: TextView = view.findViewById(R.id.textQuizTitre)
        val textDetails: TextView = view.findViewById(R.id.textQuizDetails)
        val textStatut: TextView = view.findViewById(R.id.textQuizStatut)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.textTitre.text = quiz.titre
        holder.textDetails.text = "${quiz.type} • ${quiz.duree} • Limite: ${quiz.dateLimite}"
        holder.textStatut.text = quiz.statut
    }

    override fun getItemCount(): Int {
        return quizList.size
    }
}
