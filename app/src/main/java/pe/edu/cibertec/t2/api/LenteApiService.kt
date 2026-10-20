package pe.edu.cibertec.t2.api

import pe.edu.cibertec.t2.model.LenteApiModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LenteApiService {
    @GET("api/lentes")
    fun getAllLentes(): Call<List<LenteApiModel>>

    @GET("api/lentes/{id}")
    fun getLenteById(@Path("id") id: Long): Call<LenteApiModel>

    @POST("api/lentes")
    fun createLente(@Body lente: LenteApiModel): Call<LenteApiModel>

    @PUT("api/lentes/{id}")
    fun updateLente(@Path("id") id: Long, @Body lente: LenteApiModel): Call<LenteApiModel>

    @DELETE("api/lentes/{id}")
    fun deleteLente(@Path("id") id: Long): Call<Void>
}
