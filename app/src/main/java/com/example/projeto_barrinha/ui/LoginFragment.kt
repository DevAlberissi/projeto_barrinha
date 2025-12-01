package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R

class LoginFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegistrar: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etEmail = view.findViewById(R.id.etEmail)
        etSenha = view.findViewById(R.id.etSenha)
        btnLogin = view.findViewById(R.id.btnLogin)
        tvRegistrar = view.findViewById(R.id.tvRegistrar)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = requireActivity().getSharedPreferences("user_prefs", 0)
            val savedEmail = sharedPref.getString("email", "")
            val savedSenha = sharedPref.getString("senha", "")
            val savedNome = sharedPref.getString("nome", "Motorista") // pega o nome do registro

            if (email == savedEmail && senha == savedSenha) {
                // Atualiza o nome do motorista no header do NavigationView
                (activity as? MainActivity)?.atualizarNomeHeader(savedNome ?: "Motorista")

                // Limpa os campos de login
                etEmail.text.clear()
                etSenha.text.clear()

                // Navega para a tela Home usando a AÇÃO correta
                findNavController().navigate(R.id.action_nav_login_to_nav_home)
            } else {
                Toast.makeText(requireContext(), "Email ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_nav_login_to_nav_register)
        }

        return view
    }
}
