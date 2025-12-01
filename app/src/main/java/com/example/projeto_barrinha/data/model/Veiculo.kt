package com.example.projeto_barrinha.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "veiculos")
data class Veiculo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val ano: Int,
    val placa: String,
    val cor: String,
    val assentos: Int
)
