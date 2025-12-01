package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.data.AppDatabase
import com.example.projeto_barrinha.databinding.FragmentEditVeiculosBinding
import com.example.projeto_barrinha.model.Veiculo
import kotlinx.coroutines.launch

class EditVeiculoFragment : Fragment() {

    private var _binding: FragmentEditVeiculosBinding? = null
    private val binding get() = _binding!!

    private var veiculoId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditVeiculosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Receber o ID vindo da lista
        veiculoId = arguments?.getInt("veiculoId") ?: 0

        if (veiculoId > 0) {
            carregarDados(veiculoId)
        }

        binding.btnSalvarEditVeiculo.setOnClickListener {
            atualizarVeiculo()
        }
    }

    private fun carregarDados(id: Int) {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val veiculo = db.veiculoDao().getById(id)

            if (veiculo != null) {
                binding.etNomeVeiculoEdit.setText(veiculo.nome)
                binding.etAnoVeiculoEdit.setText(veiculo.ano.toString())
                binding.etPlacaVeiculoEdit.setText(veiculo.placa)
                binding.etCorVeiculoEdit.setText(veiculo.cor)
                binding.etAssentosVeiculoEdit.setText(veiculo.assentos.toString())
            }
        }
    }

    private fun atualizarVeiculo() {
        val nome = binding.etNomeVeiculoEdit.text.toString()
        val placa = binding.etPlacaVeiculoEdit.text.toString()
        // Conversões seguras para Inteiro
        val ano = binding.etAnoVeiculoEdit.text.toString().toIntOrNull() ?: 0
        val assentos = binding.etAssentosVeiculoEdit.text.toString().toIntOrNull() ?: 0
        val cor = binding.etCorVeiculoEdit.text.toString()

        if (nome.isNotEmpty() && placa.isNotEmpty()) {
            val veiculoAtualizado = Veiculo(
                id = veiculoId, // Importante manter o ID para ser um Update
                nome = nome,
                ano = ano,
                placa = placa,
                cor = cor,
                assentos = assentos
            )

            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(requireContext())
                db.veiculoDao().update(veiculoAtualizado)

                Toast.makeText(context, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        } else {
            Toast.makeText(context, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}