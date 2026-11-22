package isetb.tp2.quizpro

data class Question(
    val idQuest: Int? = null,
    val typeQuest: String,
    val contenu: String,
    val image: String?,
    val document: String?,
    val reponceCorrect: String?,
    val duree: String?
)
