package com.example.projeto_barrinha.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto_barrinha.dao.AlunoDao
import com.example.projeto_barrinha.model.Aluno
import com.example.projeto_barrinha.model.Responsavel
import com.example.projeto_barrinha.model.Veiculo

@Database(entities = [Aluno::class, Responsavel::class, Veiculo::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alunoDao(): AlunoDao
    abstract fun responsavelDao(): ResponsavelDao
    abstract fun veiculoDao(): VeiculoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "banco_barrinha"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}
