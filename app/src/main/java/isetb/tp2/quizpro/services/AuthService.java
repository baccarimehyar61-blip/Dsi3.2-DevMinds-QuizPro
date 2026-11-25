package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Utilisateur
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/inscription")
    fun inscrire(@Body utilisateur: Utilisateur): Call<Utilisateur>

    @POST("api/auth/login")
    fun login(@Body utilisateur: Utilisateur): Call<Utilisateur>
}