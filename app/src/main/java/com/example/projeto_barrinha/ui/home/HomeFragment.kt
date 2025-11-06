package com.example.projeto_barrinha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Estado inicial do período
    private var periodoAtual = "Manhã"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCadastrarAluno.setOnClickListener {
            findNavController().navigate(R.id.nav_cad_alunos)
        }

        binding.buttonVerLista.setOnClickListener {
            findNavController().navigate(R.id.nav_alunos)
        }

        // --- NOVO: Alternar Período Atual ---
        binding.cardPeriodoAtual.setOnClickListener {
            // Alterna o período
            periodoAtual = if (periodoAtual == "Manhã") "Tarde" else "Manhã"

            // Atualiza os textos do card
            binding.textPeriodo.text = periodoAtual
            binding.labelAlunosPeriodo.text = "Alunos no Período ($periodoAtual)"

            // Atualiza os números de exemplo (substitua pelos dados reais)
            if (periodoAtual == "Manhã") {
                binding.textConfirmados.text = "14"
                binding.textTotal.text = "20"
            } else {
                binding.textConfirmados.text = "10"
                binding.textTotal.text = "18"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
