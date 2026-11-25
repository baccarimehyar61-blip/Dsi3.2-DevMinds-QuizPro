package isetb.tp2.quizpro.classes

data class Quiz(
    val idQuiz: Long? = null,
    val titre: String,
    val description: String,
    val objectif: String,
    val duree: Int,
    val dateLimite: String?,
    val dateCreation: String?,
    val nbrTentatives: Int,
    val melangeQust: Boolean,
    val melangeRep: Boolean,
    val typeQuiz: String,
    val statut: String,
    val score: Float?
)
