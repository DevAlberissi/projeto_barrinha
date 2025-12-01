package com.example.projeto_barrinha.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.data.AppDatabase // Verifique se o import está correto (data ou com.example...)
import com.example.projeto_barrinha.databinding.FragmentVeiculosBinding
import com.example.projeto_barrinha.model.Veiculo // Verifique se o import está correto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VeiculosFragment : Fragment() {

    private var _binding: FragmentVeiculosBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: VeiculoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVeiculosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener = object : VeiculoListener {

            override fun onEditClick(veiculo: Veiculo) {
                val bundle = Bundle().apply {
                    putInt("veiculoId", veiculo.id)
                }
                findNavController().navigate(R.id.action_nav_veiculos_to_nav_edit_veiculo, bundle)
            }

            override fun onDeleteClick(veiculo: Veiculo) {
                confirmarExclusao(veiculo)
            }
        }

        adapter = VeiculoAdapter(emptyList(), listener)
        binding.rvVeiculos.adapter = adapter

        // Observa os dados do banco
        val veiculoDao = AppDatabase.getDatabase(requireContext()).veiculoDao()
        veiculoDao.getAll().observe(viewLifecycleOwner) { veiculos ->
            adapter.updateData(veiculos)
        }

        binding.fabAddVeiculo.setOnClickListener {
            findNavController().navigate(R.id.action_nav_veiculos_to_nav_cadastro_veiculo)
        }
    }

    private fun confirmarExclusao(veiculo: Veiculo) {
        AlertDialog.Builder(requireContext())
            .setTitle("Excluir Veículo?")
            .setMessage("Tem certeza que deseja excluir o veículo ${veiculo.nome}?")
            .setPositiveButton("Sim") { _, _ ->
                excluirVeiculo(veiculo)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun excluirVeiculo(veiculo: Veiculo) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            db.veiculoDao().delete(veiculo)

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Veículo removido!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}