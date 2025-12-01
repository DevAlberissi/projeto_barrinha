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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Responsavel

class CadResponsavelFragment : Fragment() {

    private var idResponsavelAtual: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cad_responsavel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNome = view.findViewById<EditText>(R.id.etRespNome)
        val etCpf = view.findViewById<EditText>(R.id.etRespCpf)
        val etTelefone = view.findViewById<EditText>(R.id.etRespTelefone)
        val etEndereco = view.findViewById<EditText>(R.id.etRespEndereco)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvarResponsavel)
        val tvTitulo = view.findViewById<TextView>(R.id.tvTituloResponsavel) // Se tiveres ID no título

        val idRecebido = arguments?.getInt("idResponsavel", 0) ?: 0

        if (idRecebido > 0) {
            idResponsavelAtual = idRecebido
            tvTitulo.text = "Editar Responsável"
            btnSalvar.text = "Atualizar"

            carregarDados(idRecebido, etNome, etCpf, etTelefone, etEndereco)
        }

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString()
            val cpf = etCpf.text.toString()
            val telefone = etTelefone.text.toString()
            val endereco = etEndereco.text.toString()

            if (nome.isNotEmpty() && telefone.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = AppDatabase.getDatabase(requireContext())

                    if (idResponsavelAtual != null) {
                        val respEditado = Responsavel(
                            id = idResponsavelAtual!!,
                            nome = nome,
                            cpf = cpf,
                            telefone = telefone,
                            endereco = endereco
                        )
                        db.responsavelDao().atualizar(respEditado)
                    } else {
                        val novoResp = Responsavel(
                            nome = nome,
                            cpf = cpf,
                            telefone = telefone,
                            endereco = endereco
                        )
                        db.responsavelDao().inserir(novoResp)
                    }

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            } else {
                Toast.makeText(context, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun carregarDados(id: Int, etNome: EditText, etCpf: EditText, etTel: EditText, etEnd: EditText) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val responsavel = db.responsavelDao().buscarPorId(id)

            withContext(Dispatchers.Main) {
                responsavel?.let {
                    etNome.setText(it.nome)
                    etCpf.setText(it.cpf)
                    etTel.setText(it.telefone)
                    etEnd.setText(it.endereco)
                }
            }
        }
    }
}