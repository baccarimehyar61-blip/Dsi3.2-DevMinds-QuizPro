package isetb.tp2.quizpro

import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("questions")
    fun getquestion(): Call<List<Question>>

    @POST("questions")
    fun addQuestion(@Body question: Question): Call<Question>

    @PUT("questions/{id}")
    fun updateQuestion(@Path("id") id: Int, @Body question: Question): Call<Question>

    @DELETE("questions/{id}")
    fun deleteQuestion(@Path("id") id: Int): Call<Void>

}
