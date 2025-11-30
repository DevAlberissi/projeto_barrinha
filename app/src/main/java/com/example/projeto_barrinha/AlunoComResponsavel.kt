package com.example.projeto_barrinha

import androidx.room.Embedded
import androidx.room.Relation

data class AlunoComResponsavel(
    @Embedded val aluno: Aluno,

    @Relation(
        parentColumn = "responsavelId",
        entityColumn = "id"
    )
    val responsavel: Responsavel
)