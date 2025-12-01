package com.example.projeto_barrinha.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.projeto_barrinha.data.model.Veiculo

@Dao
interface VeiculoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(veiculo: Veiculo)
}
