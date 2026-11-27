package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Utilisateur
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface utilisateur {

    @GET("utl")
    fun getUtilisateurs(): Call<List<Utilisateur>>

    @POST("utl")
    fun addUtilisateur(@Body utilisateur: Utilisateur): Call<Utilisateur>
}