package com.example.projeto_barrinha.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.projeto_barrinha.model.Aluno
import com.example.projeto_barrinha.model.AlunoComResponsavel

@Dao
interface AlunoDao {

    @Insert
    suspend fun inserir(aluno: Aluno)

    @Update
    suspend fun atualizar(aluno: Aluno)  // método necessário para editar

    @Delete
    suspend fun deletar(aluno: Aluno)

    @Query("SELECT * FROM alunos")
    suspend fun listar(): List<Aluno>

    @Query("SELECT * FROM alunos WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Aluno?  // método necessário para editar

    @Query("SELECT COUNT(*) FROM alunos WHERE periodo = :periodo")
    suspend fun contarPorPeriodo(periodo: String): Int

    @Transaction
    @Query("SELECT * FROM alunos")
    suspend fun listarComResponsavel(): List<AlunoComResponsavel>
}
