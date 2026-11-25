package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuizService {
    @GET("quizs")
    fun getQuizs(): Call<List<Quiz>>

    @GET("quizs/{idQuiz}")
    fun getQuizBy(@Path("idQuiz") idQuiz: Int): Call<Quiz>

    @POST("quizs")
    fun addQuiz(@Body quiz: Quiz): Call<Quiz>

    @PUT("quizs/{idQuiz}")
    fun updateQuiz(@Path("idQuiz") idQuiz: Int, @Body quizs: Quiz): Call<Void>

    @DELETE("quiz/{idQuiz}")
    fun deleteQuiz(@Path("idQuiz") idQuiz: Int): Call<Void>

}