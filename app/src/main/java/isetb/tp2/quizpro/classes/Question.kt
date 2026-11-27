package isetb.tp2.quizpro.classes

data class Question(
    val idQuest: Int? = null,
    val typeQuest: String,
    val contenu: String,
    val image: String?,
    val document: String?,
    val reponceCorrect: String?,
    val duree: Int?,
    val id_quiz: Int
)
/*data class Question(
    val id: Int = 0,
    val questionText: String,
    val correctOptionIndex: Int
)*/
data class ChoiceItem(
    val id: Int,
    val text: String
)

data class DbQuestion(
    val id: Int,
    val questionText: String,
    val choices: List<ChoiceItem>
)