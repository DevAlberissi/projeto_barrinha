package com.example.projeto_barrinha.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.Veiculo

@Dao
interface VeiculoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(veiculo: Veiculo)

    @Query("SELECT * FROM veiculos ORDER BY nome ASC")
    fun getAll(): LiveData<List<Veiculo>>
}
