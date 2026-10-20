package pe.edu.cibertec.final.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.cibertec.final.databinding.ItemProductBinding
import pe.edu.cibertec.final.model.Product

class ProductAdapter(
    private var items: List<Product>,
    private val isAdminMode: Boolean = false,
    private val onActionClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productCategory.text = product.category
            binding.productPrice.text = "S/ ${String.format("%.2f", product.price)}"
            binding.productAction.text = if (isAdminMode) "Editar" else "Agregar"

            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .centerCrop()
                .into(binding.productImage)

            binding.productAction.setOnClickListener {
                onActionClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }
}
