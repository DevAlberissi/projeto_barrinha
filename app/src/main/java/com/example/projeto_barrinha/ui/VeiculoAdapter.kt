package com.example.projeto_barrinha.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_barrinha.databinding.ItemVeiculoBinding
import model.Veiculo

// 1. Crie esta interface para "avisar" a Activity
interface VeiculoListener {
    fun onEditClick(veiculo: Veiculo)
    fun onDeleteClick(veiculo: Veiculo)
}

// 2. Adicione o listener no construtor
class VeiculoAdapter(
    private var veiculos: List<Veiculo>,
    private val listener: VeiculoListener
) : RecyclerView.Adapter<VeiculoAdapter.VeiculoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculoViewHolder {
        val binding = ItemVeiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VeiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VeiculoViewHolder, position: Int) {
        holder.bind(veiculos[position], listener) // Passa o listener para o bind
    }

    override fun getItemCount() = veiculos.size

    fun updateData(newVeiculos: List<Veiculo>) {
        veiculos = newVeiculos
        notifyDataSetChanged()
    }

    class VeiculoViewHolder(private val binding: ItemVeiculoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // 3. Receba o listener aqui no bind
        fun bind(veiculo: Veiculo, listener: VeiculoListener) {
            binding.tvNomeVeiculo.text = veiculo.nome
            binding.tvPlacaVeiculo.text = "Placa: ${veiculo.placa}"
            binding.tvAnoCorVeiculo.text = "Ano/Cor: ${veiculo.ano} / ${veiculo.cor}"
            binding.tvAssentosVeiculo.text = "Assentos: ${veiculo.assentos}"

            // --- AQUI É ONDE VOCÊ COLOCA A AÇÃO DOS BOTÕES ---

            // Botão Editar
            binding.btnEditar.setOnClickListener {
                listener.onEditClick(veiculo)
            }

            // Botão Excluir
            binding.btnExcluir.setOnClickListener {
                listener.onDeleteClick(veiculo)
            }
        }
    }
}