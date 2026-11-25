package isetb.tp2.quizpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isetb.tp2.quizpro.R
import isetb.tp2.quizpro.classes.Quiz

class QuizFormAdapter (private val quizList: List<Quiz>,
                       private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<QuizFormAdapter.QuizViewHolder>(){

    class QuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitre: TextView = view.findViewById(R.id.textQuizTitre)
        val textDetails: TextView = view.findViewById(R.id.textQuizDetails)
        val textStatut: TextView = view.findViewById(R.id.textQuizStatut)
    }
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): QuizFormAdapter.QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizFormAdapter.QuizViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.textTitre.text = quiz.titre
        holder.textDetails.text = "${quiz.typeQuiz} • ${quiz.duree} • Limite: ${quiz.dateLimite}"
        holder.textStatut.text = quiz.statut

        holder.itemView.setOnClickListener {
            onItemClick(quiz.idQuiz!!)
        }
    }

    override fun getItemCount(): Int {
        return quizList.size
    }
}