package isetb.tp2.quizpro.services

import isetb.tp2.quizpro.classes.Question
import isetb.tp2.quizpro.classes.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {
    @GET("ques")
    fun getQues(): Call<List<Question>>

    @GET("ques/{idQues}")
    fun getQuesById(@Path("idQues") idQues: Int): Call<Question>

    @POST("ques")
    fun addQues(@Body ques: Question): Call<Question>

    @PUT("ques/{idQues}")
    fun updateQues(@Path("idQues") idQues: Int, @Body ques: Question): Call<Void>

    @DELETE("ques/{idQues}")
    fun deleteQues(@Path("idQues") idQues: Int): Call<Void>
}