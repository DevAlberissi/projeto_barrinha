package com.example.projeto_barrinha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto_barrinha.adapter.AlunoAdapter
import com.example.projeto_barrinha.databinding.FragmentAlunosBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlunosFragment : Fragment() {

    private var _binding: FragmentAlunosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlunosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botão para ir até a tela de cadastro
        binding.buttonAdicionar.setOnClickListener {
            findNavController().navigate(R.id.action_nav_alunos_to_nav_cad_alunos)
        }

        // Configura o RecyclerView
        binding.recyclerAlunos.layoutManager = LinearLayoutManager(requireContext())

        // Carrega os alunos do banco
        carregarAlunos()
    }

    private fun carregarAlunos() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val alunos = db.alunoDao().listar()

            withContext(Dispatchers.Main) {
                binding.recyclerAlunos.adapter = AlunoAdapter(
                    lista = alunos,
                    onEditar = { aluno ->
                        // Passa os dados do aluno para o fragment de edição
                        val bundle = Bundle().apply {
                            putInt("id", aluno.id)
                            putString("nome", aluno.nome)
                            putString("escola", aluno.escola)
                            putString("endereco", aluno.endereco)
                            putString("periodo", aluno.periodo)
                            putString("responsavel", aluno.responsavel)
                            putString("curso", aluno.curso) // ✅ Agora o curso será enviado
                        }
                        findNavController().navigate(R.id.action_nav_alunos_to_nav_edit_alunos, bundle)
                    },
                    onExcluir = { aluno ->
                        // Excluir aluno do banco
                        lifecycleScope.launch {
                            db.alunoDao().deletar(aluno)
                            carregarAlunos() // Recarrega a lista após exclusão
                        }
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
