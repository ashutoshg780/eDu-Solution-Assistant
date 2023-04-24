package com.tgb.edusolutionassistant

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RETROFIT_API_Object {
    private const val BASE_URL = "https://edusolutionassistant.pythonanywhere.com"
    val apiInterface: RETROFIT_API_Interface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RETROFIT_API_Interface::class.java)
    }
}