package com.example.projeto_barrinha

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

class RegisterFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvGoToLogin: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        etEmail = view.findViewById(R.id.etEmail)
        etSenha = view.findViewById(R.id.etSenha)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)
        tvGoToLogin = view.findViewById(R.id.tvGoToLogin)

        btnRegistrar.setOnClickListener {
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                // Salvar no SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("user_prefs", 0)
                with(sharedPref.edit()) {
                    putString("email", email)
                    putString("senha", senha)
                    apply()
                }

                Toast.makeText(requireContext(), "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_nav_register_to_nav_login)
            }
        }

        tvGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_nav_register_to_nav_login)
        }

        return view
    }
}
