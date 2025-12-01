package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.databinding.FragmentVeiculosBinding
import com.example.projeto_barrinha.data.AppDatabase

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

        adapter = VeiculoAdapter(emptyList())
        binding.rvVeiculos.adapter = adapter

        val veiculoDao = AppDatabase.getDatabase(requireContext()).veiculoDao()
        veiculoDao.getAll().observe(viewLifecycleOwner) { veiculos ->
            adapter.updateData(veiculos)
        }

        binding.fabAddVeiculo.setOnClickListener {
            findNavController().navigate(R.id.action_nav_veiculos_to_nav_cadastro_veiculo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
