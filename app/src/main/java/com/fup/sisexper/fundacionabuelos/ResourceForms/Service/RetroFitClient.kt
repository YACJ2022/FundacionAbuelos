package com.fup.sisexper.fundacionabuelos.ResourceForms.Service
import com.fup.sisexper.fundacionabuelos.ResourceForms.Sedes.ApiServiceSedes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private const val BASE_URL = "https://fundacionabueloss.onrender.com"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiServiceSedes by lazy {
        retrofit.create(ApiServiceSedes::class.java)
    }
}