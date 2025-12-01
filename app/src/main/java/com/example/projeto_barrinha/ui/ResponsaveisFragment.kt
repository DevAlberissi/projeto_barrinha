package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.adapter.ResponsavelAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Responsavel

class ResponsaveisFragment : Fragment() {

    private lateinit var recyclerResponsaveis: RecyclerView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_responsaveis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerResponsaveis = view.findViewById(R.id.recyclerResponsaveis)
        fabAdd = view.findViewById(R.id.fabAddResponsavel)

        recyclerResponsaveis.layoutManager = LinearLayoutManager(requireContext())

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.nav_cad_responsavel)
        }

        carregarLista()
    }

    private fun carregarLista() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val lista = db.responsavelDao().listarTodos()

            withContext(Dispatchers.Main) {
                recyclerResponsaveis.adapter = ResponsavelAdapter(
                    lista = lista,
                    onEditar = { responsavel ->
                        val bundle = Bundle()
                        bundle.putInt("idResponsavel", responsavel.id)
                        findNavController().navigate(R.id.nav_cad_responsavel, bundle)
                    },
                    onExcluir = { responsavel ->
                        confirmarExclusao(responsavel)
                    }
                )
            }
        }
    }

    private fun excluirResponsavel(responsavel: Responsavel) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            db.responsavelDao().deletar(responsavel)

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Responsável removido!", Toast.LENGTH_SHORT).show()
                carregarLista()
            }
        }
    }

    private fun confirmarExclusao(responsavel: Responsavel) {
        AlertDialog.Builder(requireContext())
            .setTitle("Excluir Responsável?")
            .setMessage("Tem certeza que deseja excluir ${responsavel.nome}?\n\nATENÇÃO: Todos os alunos vinculados a este responsável também serão apagados!")
            .setPositiveButton("Sim") { _, _ ->
                excluirResponsavel(responsavel)
            }
            .setNegativeButton("Não", null)
            .show()
    }
}