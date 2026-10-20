package pe.edu.cibertec.t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pe.edu.cibertec.t2.databinding.FragmentAdminLoginBinding

class AdminLoginFragment : Fragment() {

    private var _binding: FragmentAdminLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text?.toString() ?: ""
            val password = binding.etPassword.text?.toString() ?: ""

            if (MockAuthManager.validateCredentials(username, password)) {
                SessionManager.isAdminLoggedIn = true
                findNavController().navigate(R.id.adminDashboardFragment)
            } else {
                Toast.makeText(requireContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}