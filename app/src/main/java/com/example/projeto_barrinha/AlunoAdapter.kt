package com.example.projeto_barrinha.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.Aluno
import com.example.projeto_barrinha.R

class AlunoAdapter(
    private val lista: List<Aluno>,
    private val onEditar: (Aluno) -> Unit,
    private val onExcluir: (Aluno) -> Unit
) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    class AlunoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtEscola: TextView = view.findViewById(R.id.txtEscola)
        val txtEndereco: TextView = view.findViewById(R.id.txtEndereco)
        val txtResponsavel: TextView = view.findViewById(R.id.txtResponsavel)
        val txtPeriodo: TextView = view.findViewById(R.id.txtPeriodo)
        val txtCurso: TextView = view.findViewById(R.id.txtCurso)
        val btnEditar: ImageButton = view.findViewById(R.id.btnEditar)
        val btnExcluir: ImageButton = view.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = lista[position]

        // Preenche os campos da lista
        holder.txtNome.text = aluno.nome
        holder.txtEscola.text = aluno.escola
        holder.txtEndereco.text = aluno.endereco
        holder.txtResponsavel.text = aluno.responsavel
        holder.txtPeriodo.text = aluno.periodo
        holder.txtCurso.text = aluno.curso

        // Botão Editar
        holder.btnEditar.setOnClickListener {
            Log.d("AlunoAdapter", "Editar clicado: ${aluno.nome}")
            onEditar(aluno) // Passa o objeto completo
        }

        // Botão Excluir
        holder.btnExcluir.setOnClickListener {
            Log.d("AlunoAdapter", "Excluir clicado: ${aluno.nome}")
            onExcluir(aluno)
        }
    }

    override fun getItemCount(): Int = lista.size
}
