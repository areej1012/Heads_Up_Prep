package com.example.headsup

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("celebrities/")
    fun getCelebrities(): Call<ArrayList<Celebrities>>
    @GET("/celebrities/{pk}")
    fun getCelebrity(@Path("pk") pk: Int): Call<Celebrities>
    @POST("celebrities/")
    fun addCelebrity(@Body celebrities: Celebrities): Call<Celebrities>
    @PUT("/celebrities/{pk}")
    fun updateCelebrity(@Path("pk") pk:Int , @Body celebrity: Celebrities ) : Call<Celebrities>
    @DELETE("/celebrities/{pk}")
    fun deleteCelebrity(@Path("pk") pk : Int) : Call<Void>

}