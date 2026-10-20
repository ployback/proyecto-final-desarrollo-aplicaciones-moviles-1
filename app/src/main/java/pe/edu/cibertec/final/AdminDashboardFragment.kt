package pe.edu.cibertec.final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.cibertec.final.adapter.ProductAdapter
import pe.edu.cibertec.final.databinding.FragmentAdminDashboardBinding
import pe.edu.cibertec.final.model.LenteApiModel
import pe.edu.cibertec.final.model.Product
import pe.edu.cibertec.final.repository.LenteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    private val repository by lazy {
        LenteRepository(requireContext().applicationContext)
    }
    private var selectedLenteId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!SessionManager.isAdminLoggedIn) {
            findNavController().navigate(R.id.adminLoginFragment)
            return
        }

        binding.rvAdminProducts.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProductAdapter(emptyList(), true) { product ->
            selectedLenteId = product.id.toLong()
            fillFormWithProduct(product)
            Toast.makeText(requireContext(), "Editando ${product.name}", Toast.LENGTH_SHORT).show()
        }
        binding.rvAdminProducts.adapter = adapter

        binding.btnLogout.setOnClickListener {
            SessionManager.isAdminLoggedIn = false
            findNavController().navigate(R.id.shopFragment)
        }

        binding.btnRefresh.setOnClickListener { loadLentes() }
        binding.btnAddLente.setOnClickListener { saveLente() }
        binding.btnDeleteSelected.setOnClickListener {
            val id = selectedLenteId
            if (id == null) {
                Toast.makeText(requireContext(), "Selecciona un lente para eliminar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            deleteLente(id)
        }

        loadLentes()
    }

    private fun loadLentes() {
        val cachedProducts = repository.getAllLocal().map { it.toProduct() }
        if (cachedProducts.isNotEmpty()) {
            (binding.rvAdminProducts.adapter as? ProductAdapter)?.submitList(cachedProducts)
        }

        repository.getAll().enqueue(object : Callback<List<LenteApiModel>> {
            override fun onResponse(
                call: Call<List<LenteApiModel>>,
                response: Response<List<LenteApiModel>>
            ) {
                if (!response.isSuccessful || response.body() == null) {
                    if (cachedProducts.isEmpty()) {
                        Toast.makeText(requireContext(), "No se pudo cargar la lista", Toast.LENGTH_SHORT).show()
                    }
                    return
                }

                val products = response.body()!!.map { it.toProduct() }
                repository.saveAllLocal(response.body()!!)
                (binding.rvAdminProducts.adapter as? ProductAdapter)?.submitList(products)
            }

            override fun onFailure(call: Call<List<LenteApiModel>>, t: Throwable) {
                if (cachedProducts.isNotEmpty()) {
                    Toast.makeText(requireContext(), "Mostrando datos guardados localmente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun saveLente() {
        val imagenPath = when ((0..3).random()) {
            0 -> "android.resource://pe.edu.cibertec.final/drawable/banner_girl"
            1 -> "android.resource://pe.edu.cibertec.final/drawable/banner_guy"
            2 -> "android.resource://pe.edu.cibertec.final/drawable/logo_trans"
            else -> "android.resource://pe.edu.cibertec.final/drawable/default_avatar"
        }

        val lente = LenteApiModel(
            id = selectedLenteId ?: 0L,
            nombre = binding.etLenteNombre.text.toString().trim(),
            marca = binding.etLenteMarca.text.toString().trim(),
            precio = binding.etLentePrecio.text.toString().toDoubleOrNull() ?: 0.0,
            stock = binding.etLenteStock.text.toString().toIntOrNull() ?: 0,
            descripcion = binding.etLenteDescripcion.text.toString().trim(),
            imagenPath = imagenPath
        )

        val request = if (selectedLenteId != null) {
            repository.update(selectedLenteId!!, lente)
        } else {
            repository.create(lente)
        }

        request.enqueue(object : Callback<LenteApiModel> {
            override fun onResponse(call: Call<LenteApiModel>, response: Response<LenteApiModel>) {
                if (response.isSuccessful) {
                    selectedLenteId = null
                    clearForm()
                    loadLentes()
                    Toast.makeText(requireContext(), "Lente guardado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "No se pudo guardar el lente", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LenteApiModel>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al guardar: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deleteLente(id: Long) {
        repository.delete(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    selectedLenteId = null
                    clearForm()
                    loadLentes()
                    Toast.makeText(requireContext(), "Lente eliminado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "No se pudo eliminar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al eliminar: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillFormWithProduct(product: Product) {
        binding.etLenteNombre.setText(product.name)
        binding.etLenteMarca.setText(product.category)
        binding.etLentePrecio.setText(product.price.toString())
        binding.etLenteStock.setText("0")
        binding.etLenteDescripcion.setText("")
    }

    private fun clearForm() {
        binding.etLenteNombre.setText("")
        binding.etLenteMarca.setText("")
        binding.etLentePrecio.setText("")
        binding.etLenteStock.setText("")
        binding.etLenteDescripcion.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
