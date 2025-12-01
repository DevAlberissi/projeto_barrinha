package com.example.projeto_barrinha.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responsaveis")
data class Responsavel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var nome: String,
    var telefone: String,
    var cpf: String,
    var endereco: String
)