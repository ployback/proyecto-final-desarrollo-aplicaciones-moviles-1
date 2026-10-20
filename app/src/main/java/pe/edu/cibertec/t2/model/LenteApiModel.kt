package pe.edu.cibertec.t2.model

data class LenteApiModel(
    val id: Long = 0,
    val nombre: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val descripcion: String = "",
    val imagenPath: String = ""
) {
    fun toProduct(): Product = Product(
        id = id.toInt(),
        name = nombre,
        category = marca,
        price = precio,
        imageUrl = imagenPath.ifBlank { "android.resource://pe.edu.cibertec.t2/drawable/default_avatar" }
    )
}
