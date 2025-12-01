package com.example.projeto_barrinha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.projeto_barrinha.databinding.FragmentCadastroVeiculoBinding
import com.example.projeto_barrinha.data.AppDatabase
import kotlinx.coroutines.launch
import com.example.projeto_barrinha.model.Veiculo

class CadastroVeiculoFragment : Fragment() {

    private var _binding: FragmentCadastroVeiculoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroVeiculoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSalvarVeiculo.setOnClickListener {
            salvarVeiculo()
        }
    }

    private fun salvarVeiculo() {
        val nome = binding.etNomeVeiculo.text.toString().trim()
        val anoStr = binding.etAnoVeiculo.text.toString().trim()
        val placa = binding.etPlacaVeiculo.text.toString().trim()
        val cor = binding.etCorVeiculo.text.toString().trim()
        val assentosStr = binding.etAssentosVeiculo.text.toString().trim()

        if (nome.isEmpty() || anoStr.isEmpty() || placa.isEmpty() || cor.isEmpty() || assentosStr.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val ano = anoStr.toIntOrNull()
        val assentos = assentosStr.toIntOrNull()

        if (ano == null || assentos == null) {
            Toast.makeText(requireContext(), "Ano e assentos devem ser números", Toast.LENGTH_SHORT).show()
            return
        }

        val veiculo = Veiculo(nome = nome, ano = ano, placa = placa, cor = cor, assentos = assentos)

        lifecycleScope.launch {
            val veiculoDao = AppDatabase.getDatabase(requireContext()).veiculoDao()
            veiculoDao.insert(veiculo)
            Toast.makeText(requireContext(), "Veículo salvo com sucesso!", Toast.LENGTH_SHORT).show()
            // Limpar campos ou navegar para outra tela
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
