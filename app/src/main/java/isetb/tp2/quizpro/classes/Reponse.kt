package isetb.tp2.quizpro.classes

data class Reponse(
    val idRep: Int? = null,
    val contenu: String,
    val explication: String?,
    val pointObtenu: Int,
    val dateR: String?
)