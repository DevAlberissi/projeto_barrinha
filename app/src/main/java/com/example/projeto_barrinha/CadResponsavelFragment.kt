package com.example.projeto_barrinha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadResponsavelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cad_responsavel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNome = view.findViewById<EditText>(R.id.etRespNome)
        val etCpf = view.findViewById<EditText>(R.id.etRespCpf)
        val etTelefone = view.findViewById<EditText>(R.id.etRespTelefone)
        val etEndereco = view.findViewById<EditText>(R.id.etRespEndereco)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvarResponsavel)

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString()
            val cpf = etCpf.text.toString()
            val telefone = etTelefone.text.toString()
            val endereco = etEndereco.text.toString()

            if (nome.isNotEmpty() && telefone.isNotEmpty()) {

                val novoResponsavel = Responsavel(
                    nome = nome,
                    cpf = cpf,
                    telefone = telefone,
                    endereco = endereco
                )

                lifecycleScope.launch(Dispatchers.IO) {
                    val db = AppDatabase.getDatabase(requireContext())
                    db.responsavelDao().inserir(novoResponsavel)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Responsável salvo com sucesso!", Toast.LENGTH_SHORT).show()

                        findNavController().popBackStack()
                    }
                }
            } else {
                Toast.makeText(context, "Preencha pelo menos Nome e Telefone!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}