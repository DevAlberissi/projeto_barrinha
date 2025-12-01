package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.data.AppDatabase
import com.example.projeto_barrinha.data.api.RetrofitClient
import com.example.projeto_barrinha.model.Responsavel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadResponsavelFragment : Fragment() {

    private var idResponsavelAtual: Int? = null

    private lateinit var etNome: EditText
    private lateinit var etCpf: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etCep: EditText
    private lateinit var etRua: EditText
    private lateinit var etNumero: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cad_responsavel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNome = view.findViewById(R.id.etRespNome)
        etCpf = view.findViewById(R.id.etRespCpf)
        etTelefone = view.findViewById(R.id.etRespTelefone)

        etCep = view.findViewById(R.id.etRespCep)
        etRua = view.findViewById(R.id.etRespRua)
        etNumero = view.findViewById(R.id.etRespNumero)

        val btnSalvar = view.findViewById<Button>(R.id.btnSalvarResponsavel)
        val tvTitulo = view.findViewById<TextView>(R.id.tvTituloResponsavel)

        etCep.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cep = s.toString().replace("-", "")
                if (cep.length == 8) {
                    buscarCep(cep)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val idRecebido = arguments?.getInt("idResponsavel", 0) ?: 0
        if (idRecebido > 0) {
            idResponsavelAtual = idRecebido
            tvTitulo.text = "Editar Responsável"
            btnSalvar.text = "Atualizar"
            carregarDados(idRecebido)
        }

        btnSalvar.setOnClickListener {
            salvarResponsavel()
        }
    }

    private fun buscarCep(cep: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val endereco = RetrofitClient.api.buscarEndereco(cep)

                withContext(Dispatchers.Main) {
                    if (endereco.erro == true) {
                        Toast.makeText(context, "CEP não encontrado!", Toast.LENGTH_SHORT).show()
                    } else {
                        etRua.setText(endereco.logradouro)
                        etNumero.requestFocus()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Erro ao buscar CEP: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun salvarResponsavel() {
        val nome = etNome.text.toString()
        val cpf = etCpf.text.toString()
        val telefone = etTelefone.text.toString()

        val rua = etRua.text.toString()
        val numero = etNumero.text.toString()
        val cep = etCep.text.toString()

        if (nome.isNotEmpty() && telefone.isNotEmpty() && rua.isNotEmpty()) {

            val enderecoCompleto = "$rua, $numero - CEP: $cep"

            lifecycleScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(requireContext())

                if (idResponsavelAtual != null) {
                    val respEditado = Responsavel(
                        id = idResponsavelAtual!!,
                        nome = nome,
                        cpf = cpf,
                        telefone = telefone,
                        endereco = enderecoCompleto
                    )
                    db.responsavelDao().atualizar(respEditado)
                } else {
                    val novoResp = Responsavel(
                        nome = nome,
                        cpf = cpf,
                        telefone = telefone,
                        endereco = enderecoCompleto
                    )
                    db.responsavelDao().inserir(novoResp)
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        } else {
            Toast.makeText(context, "Preencha Nome, Telefone e Endereço!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun carregarDados(id: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val responsavel = db.responsavelDao().buscarPorId(id)

            withContext(Dispatchers.Main) {
                responsavel?.let {
                    etNome.setText(it.nome)
                    etCpf.setText(it.cpf)
                    etTelefone.setText(it.telefone)
                    etRua.setText(it.endereco)
                }
            }
        }
    }
}