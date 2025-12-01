package com.example.projeto_barrinha.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.databinding.ItemVeiculoBinding
import com.example.projeto_barrinha.model.Veiculo

interface VeiculoListener {
    fun onEditClick(veiculo: Veiculo)
    fun onDeleteClick(veiculo: Veiculo)
}

class VeiculoAdapter(
    private var veiculos: List<Veiculo>,
    private val listener: VeiculoListener
) : RecyclerView.Adapter<VeiculoAdapter.VeiculoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculoViewHolder {
        val binding = ItemVeiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VeiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VeiculoViewHolder, position: Int) {
        holder.bind(veiculos[position], listener)
    }

    override fun getItemCount() = veiculos.size

    fun updateData(newVeiculos: List<Veiculo>) {
        veiculos = newVeiculos
        notifyDataSetChanged()
    }

    class VeiculoViewHolder(private val binding: ItemVeiculoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(veiculo: Veiculo, listener: VeiculoListener) {
            binding.tvNomeVeiculo.text = veiculo.nome
            binding.tvPlacaVeiculo.text = "Placa: ${veiculo.placa}"
            binding.tvAnoCorVeiculo.text = "Ano: ${veiculo.ano} | Cor: ${veiculo.cor}"
            binding.tvAssentosVeiculo.text = "Assentos: ${veiculo.assentos}"
            binding.btnEditar.setOnClickListener {
                listener.onEditClick(veiculo)
            }

            binding.btnExcluir.setOnClickListener {
                listener.onDeleteClick(veiculo)
            }
        }
    }
}