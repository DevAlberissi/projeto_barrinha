package com.example.projeto_barrinha.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.projeto_barrinha.model.Veiculo

@Dao
interface VeiculoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(veiculo: Veiculo)

    @Update
    suspend fun update(veiculo: Veiculo)

    @Delete
    suspend fun delete(veiculo: Veiculo)

    @Query("SELECT * FROM veiculos ORDER BY nome ASC")
    fun getAll(): LiveData<List<Veiculo>>

    @Query("SELECT * FROM veiculos WHERE id = :id")
    suspend fun getById(id: Int): Veiculo?
}