package com.fup.sisexper.fundacionabuelos.ResourceForms.Sedes

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceSedes {
    @GET("/api/curso")
    fun obtenerSede(): Call<List<com.example.contactos.Sedes.item>>
    @POST("/api/curso")
    fun insertarSede(@Body curso: com.example.contactos.Sedes.item): Call<com.example.contactos.Sedes.item>
    @DELETE("/api/curso/{id}")
    fun deleteSede(@Path("id") id: Int): Call<Void>
    @PUT("/api/curso/{id}")
    fun actualizarSede(@Path("id") id: Int , @Body curso: com.example.contactos.Sedes.item  ) : Call<com.example.contactos.Sedes.item>
    @GET("/api/curso/{id}")
    fun obtenerSedePorId(@Path("id") id: Int ): Call<com.example.contactos.Sedes.item>
}