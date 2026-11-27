package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {
    @GET("ques")
    fun getquestions(): Call<List<Question>>

    @GET("ques/{idQues}")
    fun getQuesById(@Path("idQues") idQues: Int): Call<Question>

    @GET("quizs/{idQuiz}/questions")
    fun getQuesByQuiz(@Path("idQuiz") idQuiz: Int): Call<List<Question>>

    @POST("ques")
    fun addQuestion(@Body question: Question): Call<Question>

    @PUT("ques/{idQues}")
    fun updateQuestion(@Path("idQues") id: Int, @Body question: Question): Call<Question>

    @DELETE("ques/{id}")
    fun deleteQuestion(@Path("id") id: Int): Call<Void>
}