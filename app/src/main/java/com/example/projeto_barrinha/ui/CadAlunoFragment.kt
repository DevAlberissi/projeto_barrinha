package com.example.projeto_barrinha.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.projeto_barrinha.model.Aluno
import com.example.projeto_barrinha.model.Responsavel

class cad_aluno : Fragment() {
    private var listaResponsaveis: List<Responsavel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cad_aluno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNomeCompleto: EditText = view.findViewById(R.id.etNomeCompleto)
        val etEscola: EditText = view.findViewById(R.id.etEscola)
        val etEndereco: EditText = view.findViewById(R.id.etEndereco)
        val spinnerPeriodo: Spinner = view.findViewById(R.id.spinnerPeriodo)
        val spinnerResponsavel: Spinner = view.findViewById(R.id.spinnerResponsavel)
        val etCurso: EditText = view.findViewById(R.id.etCurso)
        val btnSalvar: Button = view.findViewById(R.id.btnSalvarCad)

        val periodos = arrayOf("Manhã", "Tarde")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, periodos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPeriodo.adapter = adapter
        carregarResponsaveis()

        btnSalvar.setOnClickListener {
            val nome = etNomeCompleto.text.toString()
            val escola = etEscola.text.toString()
            val endereco = etEndereco.text.toString()
            val periodo = spinnerPeriodo.selectedItem.toString()
            val curso = etCurso.text.toString()

            val posicaoResponsavel = spinnerResponsavel.selectedItemPosition

            if (posicaoResponsavel != Spinner.INVALID_POSITION && listaResponsaveis.isNotEmpty()) {
                val idResponsavel = listaResponsaveis[posicaoResponsavel].id

                if (nome.isNotEmpty() && escola.isNotEmpty() && endereco.isNotEmpty() && periodo.isNotEmpty() && curso.isNotEmpty()) {
                    val aluno = Aluno(
                        nome = nome,
                        escola = escola,
                        endereco = endereco,
                        periodo = periodo,
                        responsavelId = idResponsavel,
                        curso = curso
                    )

                    lifecycleScope.launch {
                        val db = AppDatabase.getDatabase(requireContext())
                        db.alunoDao().inserir(aluno)

                        requireActivity().runOnUiThread {
                            Toast.makeText(context, "Aluno salvo com sucesso!", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(R.id.nav_alunos)
                        }
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Selecione um responsável!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun carregarResponsaveis() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            listaResponsaveis = db.responsavelDao().listarTodos()
            withContext(Dispatchers.Main) {
                val nomes = listaResponsaveis.map { it.nome }
                val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, nomes)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view?.findViewById<Spinner>(R.id.spinnerResponsavel)?.adapter = adapter
            }
        }
    }
}
