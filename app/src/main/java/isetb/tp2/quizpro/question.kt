package isetb.tp2.quizpro

data class Question(
    val id: Int = 0,
    val questionText: String,
    val correctOptionIndex: Int
)
data class ChoiceItem(
    val id: Int,
    val text: String
)

data class DbQuestion(
    val id: Int,
    val questionText: String,
    val choices: List<ChoiceItem>
)/*
data class Question1(
    val id: Int,
    val texteQuestion: String,
    val reponseA: String,
    val reponseB: String,
    val reponseC: String,
    val reponseD: String,
    val bonneReponse: String
)*/