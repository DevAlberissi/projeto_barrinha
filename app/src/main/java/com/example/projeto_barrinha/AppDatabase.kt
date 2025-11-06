package com.example.projeto_barrinha

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto_barrinha.dao.AlunoDao

@Database(entities = [Aluno::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alunoDao(): AlunoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "banco_barrinha"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
