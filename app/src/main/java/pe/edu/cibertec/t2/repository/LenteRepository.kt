package pe.edu.cibertec.t2.repository

import pe.edu.cibertec.t2.api.LenteApiService
import pe.edu.cibertec.t2.model.LenteApiModel
import pe.edu.cibertec.t2.retrofit.ClienteRetrofit
import retrofit2.Call

class LenteRepository(
    private val service: LenteApiService = ClienteRetrofit.createLenteService()
) {

    fun getAll(): Call<List<LenteApiModel>> = service.getAllLentes()

    fun getById(id: Long): Call<LenteApiModel> = service.getLenteById(id)

    fun create(lente: LenteApiModel): Call<LenteApiModel> = service.createLente(lente)

    fun update(id: Long, lente: LenteApiModel): Call<LenteApiModel> = service.updateLente(id, lente)

    fun delete(id: Long): Call<Void> = service.deleteLente(id)
}
