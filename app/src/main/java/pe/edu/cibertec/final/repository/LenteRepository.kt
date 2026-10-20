package pe.edu.cibertec.final.repository

import android.content.Context
import pe.edu.cibertec.final.api.LenteApiService
import pe.edu.cibertec.final.database.LenteSqliteHelper
import pe.edu.cibertec.final.model.LenteApiModel
import pe.edu.cibertec.final.retrofit.ClienteRetrofit
import retrofit2.Call

class LenteRepository(
    context: Context? = null,
    private val service: LenteApiService = ClienteRetrofit.createLenteService()
) {

    private val sqliteHelper: LenteSqliteHelper? = context?.let { LenteSqliteHelper(it) }

    fun getAll(): Call<List<LenteApiModel>> = service.getAllLentes()

    fun getById(id: Long): Call<LenteApiModel> = service.getLenteById(id)

    fun create(lente: LenteApiModel): Call<LenteApiModel> = service.createLente(lente)

    fun update(id: Long, lente: LenteApiModel): Call<LenteApiModel> = service.updateLente(id, lente)

    fun delete(id: Long): Call<Void> = service.deleteLente(id)

    fun getAllLocal(): List<LenteApiModel> = sqliteHelper?.getAll() ?: emptyList()

    fun saveAllLocal(lentes: List<LenteApiModel>) {
        sqliteHelper?.saveAll(lentes)
    }
}
