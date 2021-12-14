package com.example.headsup

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
      private var retrofit: Retrofit? = null

    fun gatAllCelebrity() : Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}