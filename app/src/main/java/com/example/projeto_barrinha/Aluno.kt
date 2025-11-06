package com.example.projeto_barrinha

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alunos")
data class Aluno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var nome: String,
    var escola: String,
    var endereco: String,
    var periodo: String,
    var responsavel: String,
    var curso: String
)
