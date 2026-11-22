package isetb.tp2.quizpro

data class Utilisateur(
    val id: Int? = null,
    val nom: String,
    val prenom: String,
    val email: String,
    val motdepasse: String,
    val role: String
)
