package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {
    @GET("ques")
    fun getquestions(): Call<List<Question>>

    @GET("ques/{id}")
    fun getQuesById(@Path("id") idQues: Int): Call<Question>

    @GET("quizs/{idQuiz}/questions")
    fun getQuesByQuiz(@Path("idQuiz") idQuiz: Int): Call<List<Question>>

    @POST("ques")
    fun addQuestion(@Body question: Question): Call<Question>

    @PUT("ques/{id}")
    fun updateQuestion(@Path("id") idQuest: Int, @Body question: Question): Call<Void>

    @DELETE("ques/{id}")
    fun deleteQuestion(@Path("id") idQuest: Int): Call<Void>
}