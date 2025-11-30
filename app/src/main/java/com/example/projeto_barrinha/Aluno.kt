package com.example.projeto_barrinha

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "alunos",
    foreignKeys = [
        ForeignKey(
            entity = Responsavel::class,
            parentColumns = ["id"],
            childColumns = ["responsavelId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["responsavelId"])]
)
data class Aluno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var nome: String,
    var escola: String,
    var endereco: String,
    var periodo: String,
    var curso: String,
    var responsavelId: Int
)
