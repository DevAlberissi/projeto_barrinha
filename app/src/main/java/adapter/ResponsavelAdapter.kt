package com.example.projeto_barrinha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.R
import com.example.projeto_barrinha.model.Responsavel

class ResponsavelAdapter(
    private val lista: List<Responsavel>,
    private val onEditar: (Responsavel) -> Unit,
    private val onExcluir: (Responsavel) -> Unit
) : RecyclerView.Adapter<ResponsavelAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.tvNomeResponsavel)
        val txtTelefone: TextView = view.findViewById(R.id.tvTelefoneResponsavel)
        val btnEditar: ImageButton = view.findViewById(R.id.btnEditarResp)
        val btnExcluir: ImageButton = view.findViewById(R.id.btnExcluirResp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_responsavel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val responsavel = lista[position]

        holder.txtNome.text = responsavel.nome
        holder.txtTelefone.text = responsavel.telefone


        holder.btnEditar.setOnClickListener { onEditar(responsavel) }
        holder.btnExcluir.setOnClickListener { onExcluir(responsavel) }
    }

    override fun getItemCount(): Int = lista.size
}