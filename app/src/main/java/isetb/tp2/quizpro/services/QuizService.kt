package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuizService {
    @GET("/quiz")
    fun getQuizs(): Call<List<Quiz>>

    @GET("quiz/{id}")
    fun getQuizBy(@Path("id") idQuiz: Int): Call<Quiz>

    @POST("quiz")
    fun addQuiz(@Body quiz: Quiz): Call<Quiz>

    @PUT("quiz/{id}")
    fun updateQuiz(@Path("id") idQuiz: Int, @Body quizs: Quiz): Call<Void>

    @DELETE("quiz/{id}")
    fun deleteQuiz(@Path("id") idQuiz: Int): Call<Void>
}