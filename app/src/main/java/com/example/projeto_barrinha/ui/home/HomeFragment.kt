package com.example.projeto_barrinha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projeto_barrinha.AppDatabase
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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



        binding.cardPeriodoAtual.setOnClickListener {

            periodoAtual = if (periodoAtual == "Manhã") "Tarde" else "Manhã"

            atualizarContagem(periodoAtual)
        }

        atualizarContagem(periodoAtual)
    }

    private fun atualizarContagem(periodo: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val contagem = db.alunoDao().contarPorPeriodo(periodo)

            withContext(Dispatchers.Main) {
                binding.textPeriodo.text = periodo

                binding.labelAlunosPeriodo.text = "Alunos no Período ($periodo)"
                binding.textTotal.text = contagem.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}