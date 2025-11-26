package com.example.projeto_barrinha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.adapter.ResponsavelAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                        Toast.makeText(context, "Editar: ${responsavel.nome}", Toast.LENGTH_SHORT).show()
                    },
                    onExcluir = { responsavel ->
                        excluirResponsavel(responsavel)
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
}