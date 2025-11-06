package com.example.projeto_barrinha

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
import kotlinx.coroutines.launch

class cad_aluno : Fragment() {

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
        val etResponsavel: EditText = view.findViewById(R.id.etResponsavel)
        val etCurso: EditText = view.findViewById(R.id.etCurso)
        val btnSalvar: Button = view.findViewById(R.id.btnSalvarCad)

        val periodos = arrayOf("Manhã", "Tarde")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, periodos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPeriodo.adapter = adapter

        btnSalvar.setOnClickListener {
            val nome = etNomeCompleto.text.toString()
            val escola = etEscola.text.toString()
            val endereco = etEndereco.text.toString()
            val periodo = spinnerPeriodo.selectedItem.toString()
            val responsavel = etResponsavel.text.toString()
            val curso = etCurso.text.toString()

            if (nome.isNotEmpty() && escola.isNotEmpty() && endereco.isNotEmpty() &&
                periodo.isNotEmpty() && responsavel.isNotEmpty() && curso.isNotEmpty()
            ) {
                val aluno = Aluno(
                    nome = nome,
                    escola = escola,
                    endereco = endereco,
                    periodo = periodo,
                    responsavel = responsavel,
                    curso = curso
                )

                lifecycleScope.launch {
                    val db = AppDatabase.getDatabase(requireContext())
                    db.alunoDao().inserir(aluno)

                    requireActivity().runOnUiThread {
                        Toast.makeText(context, "Aluno salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.nav_alunos)
                    }
                }
            } else {
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
