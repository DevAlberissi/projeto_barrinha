package com.example.projeto_barrinha

import android.os.Bundle
import android.util.Patterns
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

    private lateinit var etNome: EditText
    private lateinit var etIdade: EditText
    private lateinit var etCNH: EditText
    private lateinit var etTelefone: EditText
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

        // Referências
        etNome = view.findViewById(R.id.etNome)
        etIdade = view.findViewById(R.id.etIdade)
        etCNH = view.findViewById(R.id.etCNH)
        etTelefone = view.findViewById(R.id.etTelefone)
        etEmail = view.findViewById(R.id.etEmail)
        etSenha = view.findViewById(R.id.etSenha)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)
        tvGoToLogin = view.findViewById(R.id.tvGoToLogin)

        btnRegistrar.setOnClickListener {
            if (validarCampos()) {
                // Salvar no SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("user_prefs", 0)
                val nome = etNome.text.toString().trim()
                with(sharedPref.edit()) {
                    putString("nome", nome)
                    putString("idade", etIdade.text.toString().trim())
                    putString("cnh", etCNH.text.toString().trim())
                    putString("telefone", etTelefone.text.toString().trim())
                    putString("email", etEmail.text.toString().trim())
                    putString("senha", etSenha.text.toString().trim())
                    apply()
                }

                Toast.makeText(requireContext(), "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()

                // Atualiza o nome no header do Navigation Drawer
                (activity as? MainActivity)?.atualizarNomeHeader(nome)

                // Navega para Home
                findNavController().navigate(R.id.nav_home)
            }
        }

        tvGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_nav_register_to_nav_login)
        }

        return view
    }

    private fun validarCampos(): Boolean {
        val nome = etNome.text.toString().trim()
        val idadeStr = etIdade.text.toString().trim()
        val cnh = etCNH.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString().trim()

        // Nome
        if (nome.isEmpty()) {
            etNome.error = "Informe seu nome"
            etNome.requestFocus()
            return false
        }

        // Idade
        if (idadeStr.isEmpty()) {
            etIdade.error = "Informe sua idade"
            etIdade.requestFocus()
            return false
        }
        val idade = idadeStr.toIntOrNull()
        if (idade == null || idade < 18) {
            etIdade.error = "Idade inválida (mínimo 18 anos)"
            etIdade.requestFocus()
            return false
        }

        // CNH
        if (cnh.isEmpty() || cnh.length < 5) {
            etCNH.error = "CNH inválida"
            etCNH.requestFocus()
            return false
        }

        // Telefone
        if (telefone.isEmpty() || telefone.length < 10) {
            etTelefone.error = "Telefone inválido"
            etTelefone.requestFocus()
            return false
        }

        // Email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Email inválido"
            etEmail.requestFocus()
            return false
        }

        // Senha
        if (senha.isEmpty() || senha.length < 6) {
            etSenha.error = "Senha deve ter no mínimo 6 caracteres"
            etSenha.requestFocus()
            return false
        }

        return true
    }
}
