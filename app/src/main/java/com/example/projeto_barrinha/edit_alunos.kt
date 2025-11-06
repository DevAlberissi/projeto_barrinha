package com.example.projeto_barrinha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class edit_alunos : Fragment() {

    private lateinit var etNomeCompleto: EditText
    private lateinit var etEscola: EditText
    private lateinit var etEndereco: EditText
    private lateinit var etPeriodo: EditText
    private lateinit var etResponsavel: EditText
    private lateinit var etCurso: EditText
    private lateinit var btnSalvarEdit: Button

    private var alunoId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_alunos, container, false)

        // Vincula os EditTexts
        etNomeCompleto = view.findViewById(R.id.etNomeCompleto)
        etEscola = view.findViewById(R.id.etEscola)
        etEndereco = view.findViewById(R.id.etEndereco)
        etPeriodo = view.findViewById(R.id.etPeriodo)
        etResponsavel = view.findViewById(R.id.etResponsavel)
        etCurso = view.findViewById(R.id.etCurso)
        btnSalvarEdit = view.findViewById(R.id.btnSalvarEdit)

        // Recebe dados do Bundle e preenche os campos
        arguments?.let { bundle ->
            alunoId = bundle.getInt("id")
            etNomeCompleto.setText(bundle.getString("nome"))
            etEscola.setText(bundle.getString("escola"))
            etEndereco.setText(bundle.getString("endereco"))
            etPeriodo.setText(bundle.getString("periodo"))
            etResponsavel.setText(bundle.getString("responsavel"))
            etCurso.setText(bundle.getString("curso")) // ✅ Agora o curso vem preenchido
        }

        btnSalvarEdit.setOnClickListener { salvarAluno() }

        return view
    }

    private fun salvarAluno() {
        val nome = etNomeCompleto.text.toString()
        val escola = etEscola.text.toString()
        val endereco = etEndereco.text.toString()
        val periodo = etPeriodo.text.toString()
        val responsavel = etResponsavel.text.toString()
        val curso = etCurso.text.toString()

        if (nome.isBlank() || escola.isBlank()) {
            Toast.makeText(requireContext(), "Nome e Escola são obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            if (alunoId != null) {
                val aluno = db.alunoDao().buscarPorId(alunoId!!)
                if (aluno != null) {
                    // Atualiza todos os campos
                    aluno.nome = nome
                    aluno.escola = escola
                    aluno.endereco = endereco
                    aluno.periodo = periodo
                    aluno.responsavel = responsavel
                    aluno.curso = curso // ✅ Atualiza curso também

                    // Salva no banco
                    db.alunoDao().atualizar(aluno)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Aluno atualizado!", Toast.LENGTH_SHORT).show()
                        requireActivity().onBackPressed()
                    }
                }
            }
        }
    }
}
