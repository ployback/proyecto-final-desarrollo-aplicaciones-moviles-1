package pe.edu.cibertec.t2.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val imageUrl: String,
    val isFeatured: Boolean = false,
    val isOnOffer: Boolean = false
)
