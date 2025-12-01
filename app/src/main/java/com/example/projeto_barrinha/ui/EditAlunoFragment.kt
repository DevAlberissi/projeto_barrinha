package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.projeto_barrinha.model.Responsavel

class EditAlunosFragment : Fragment() {

    private lateinit var etNomeCompleto: EditText
    private lateinit var etEscola: EditText
    private lateinit var etEndereco: EditText
    private lateinit var spinnerPeriodo: Spinner
    private lateinit var spinnerResponsavel: Spinner
    private lateinit var etCurso: EditText
    private lateinit var btnSalvarEdit: Button

    private var alunoId: Int? = null
    private var responsavelIdAtual: Int? = null
    private var listaResponsaveis: List<Responsavel> = emptyList()
    private var periodoAtual: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_alunos, container, false)

        etNomeCompleto = view.findViewById(R.id.etNomeCompleto)
        etEscola = view.findViewById(R.id.etEscola)
        etEndereco = view.findViewById(R.id.etEndereco)
        spinnerResponsavel = view.findViewById(R.id.spinnerResponsavelEdit)
        etCurso = view.findViewById(R.id.etCurso)
        btnSalvarEdit = view.findViewById(R.id.btnSalvarEdit)

        spinnerPeriodo = view.findViewById(R.id.spinnerPeriodoEdit)
        val opcoesPeriodo = arrayOf("Manhã", "Tarde")
        val adapterPeriodo = ArrayAdapter(requireContext(), R.layout.item_spinner, opcoesPeriodo)
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPeriodo.adapter = adapterPeriodo

        arguments?.let { bundle ->
            alunoId = bundle.getInt("id")
            responsavelIdAtual = bundle.getInt("responsavelId")
            etNomeCompleto.setText(bundle.getString("nome"))
            etEscola.setText(bundle.getString("escola"))
            etEndereco.setText(bundle.getString("endereco"))
            etCurso.setText(bundle.getString("curso"))
            periodoAtual = bundle.getString("periodo")

            if (periodoAtual != null) {
                val posicao = opcoesPeriodo.indexOf(periodoAtual)
                if (posicao >= 0) {
                    spinnerPeriodo.setSelection(posicao)
                }
            }
        }

        carregarResponsaveis()

        btnSalvarEdit.setOnClickListener { salvarAluno() }

        return view
    }

    private fun carregarResponsaveis() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            listaResponsaveis = db.responsavelDao().listarTodos()

            withContext(Dispatchers.Main) {
                val nomes = listaResponsaveis.map { it.nome }
                val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, nomes)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerResponsavel.adapter = adapter

                responsavelIdAtual?.let { idProcurado ->
                    val posicao = listaResponsaveis.indexOfFirst { it.id == idProcurado }
                    if (posicao >= 0) {
                        spinnerResponsavel.setSelection(posicao)
                    }
                }
            }
        }
    }

    private fun salvarAluno() {
        val nome = etNomeCompleto.text.toString()
        val escola = etEscola.text.toString()
        val endereco = etEndereco.text.toString()
        val periodo = spinnerPeriodo.selectedItem.toString()
        val curso = etCurso.text.toString()

        val posicaoSelecionada = spinnerResponsavel.selectedItemPosition

        if (posicaoSelecionada != Spinner.INVALID_POSITION && listaResponsaveis.isNotEmpty()) {
            val responsavelSelecionado = listaResponsaveis[posicaoSelecionada]

            lifecycleScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(requireContext())
                if (alunoId != null) {
                    val aluno = db.alunoDao().buscarPorId(alunoId!!)
                    if (aluno != null) {
                        aluno.nome = nome
                        aluno.escola = escola
                        aluno.endereco = endereco
                        aluno.periodo = periodo
                        aluno.responsavelId = responsavelSelecionado.id
                        aluno.curso = curso

                        db.alunoDao().atualizar(aluno)

                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Aluno atualizado!", Toast.LENGTH_SHORT).show()
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "Selecione um responsável", Toast.LENGTH_SHORT).show()
        }
    }
}