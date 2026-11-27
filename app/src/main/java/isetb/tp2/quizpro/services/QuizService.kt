package isetb.tp2.quizpro.services

import android.telecom.Call
import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuizService {
<<<<<<< HEAD
    @Get("quizs")
    fun getQuizs(): android.telecom.Call<List<Quiz>>
=======
    @GET("/quiz")
    fun getQuizs(): Call<List<Quiz>>
>>>>>>> oumayma

    @GET("quiz/{id}")
    fun getQuizBy(@Path("id") idQuiz: Int): Call<Quiz>

    @POST("quiz")
    fun addQuiz(@Body quiz: Quiz): Call<Quiz>

    @PUT("quiz/{id}")
    fun updateQuiz(@Path("id") idQuiz: Int, @Body quizs: Quiz): Call<Void>

<<<<<<< HEAD
    @delete("quizs/{idQuiz}")
    fun deleteQuiz(@Path("idQuiz") idQuiz: Int): Call<Void>
=======
    @DELETE("quiz/{id}")
    fun deleteQuiz(@Path("id") idQuiz: Int): Call<Void>
>>>>>>> oumayma
}