package com.example.projeto_barrinha.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R

class DetalhesUsuarioFragment : Fragment() {

    private lateinit var etNome: EditText
    private lateinit var etIdade: EditText
    private lateinit var etCNH: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnSalvar: Button

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalhes_usuario, container, false)

        // Referências
        etNome = view.findViewById(R.id.etNome)
        etIdade = view.findViewById(R.id.etIdade)
        etCNH = view.findViewById(R.id.etCNH)
        etTelefone = view.findViewById(R.id.etTelefone)
        etEmail = view.findViewById(R.id.etEmail)
        etSenha = view.findViewById(R.id.etSenha)
        btnSalvar = view.findViewById(R.id.btnSalvarDetalhes)

        // Inicializar SharedPreferences
        sharedPref = requireActivity().getSharedPreferences("user_prefs", 0)

        // Carregar dados salvos
        carregarDadosUsuario()

        btnSalvar.setOnClickListener {
            if (validarCampos()) {
                // Salvar no SharedPreferences
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

                Toast.makeText(requireContext(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show()

                // Atualiza o nome no header do Navigation Drawer
                (activity as? MainActivity)?.atualizarNomeHeader(nome)

                // Volta para a tela anterior (ou vai para a home)
                findNavController().popBackStack()
            }
        }

        return view
    }

    private fun carregarDadosUsuario() {
        etNome.setText(sharedPref.getString("nome", ""))
        etIdade.setText(sharedPref.getString("idade", ""))
        etCNH.setText(sharedPref.getString("cnh", ""))
        etTelefone.setText(sharedPref.getString("telefone", ""))
        etEmail.setText(sharedPref.getString("email", ""))
        etSenha.setText(sharedPref.getString("senha", ""))
    }

    private fun validarCampos(): Boolean {
        val nome = etNome.text.toString().trim()
        val idadeStr = etIdade.text.toString().trim()
        val cnh = etCNH.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString().trim()

        if (nome.isEmpty()) {
            etNome.error = "Informe seu nome"
            etNome.requestFocus()
            return false
        }

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

        if (cnh.isEmpty() || cnh.length < 5) {
            etCNH.error = "CNH inválida"
            etCNH.requestFocus()
            return false
        }

        if (telefone.isEmpty() || telefone.length < 10) {
            etTelefone.error = "Telefone inválido"
            etTelefone.requestFocus()
            return false
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Email inválido"
            etEmail.requestFocus()
            return false
        }

        if (senha.isEmpty() || senha.length < 6) {
            etSenha.error = "Senha deve ter no mínimo 6 caracteres"
            etSenha.requestFocus()
            return false
        }

        return true
    }
}