package com.example.projeto_barrinha.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.model.Aluno
import com.example.projeto_barrinha.model.AlunoComResponsavel
import com.example.projeto_barrinha.R

class AlunoAdapter(
    private val lista: List<AlunoComResponsavel>,
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
        val item = lista[position]

        // Preenche os campos da lista
        holder.txtNome.text = item.aluno.nome
        holder.txtEscola.text = item.aluno.escola
        holder.txtEndereco.text = item.aluno.endereco
        holder.txtPeriodo.text = item.aluno.periodo
        holder.txtCurso.text = item.aluno.curso
        holder.txtResponsavel.text = "Resp: ${item.responsavel.nome}"

        holder.btnEditar.setOnClickListener {
            Log.d("AlunoAdapter", "Editar clicado: ${item.aluno.nome}")
            onEditar(item.aluno) // Passa o objeto completo
        }

        holder.btnExcluir.setOnClickListener {
            Log.d("AlunoAdapter", "Excluir clicado: ${item.aluno.nome}")
            onExcluir(item.aluno)
        }
    }

    override fun getItemCount(): Int = lista.size
}
