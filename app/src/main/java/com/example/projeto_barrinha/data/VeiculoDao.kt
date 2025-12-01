package com.example.projeto_barrinha.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projeto_barrinha.model.Veiculo

@Dao
interface VeiculoDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(veiculo: Veiculo)

    @Query("SELECT * FROM veiculos ORDER BY nome ASC")
    fun getAll(): LiveData<List<Veiculo>>
}