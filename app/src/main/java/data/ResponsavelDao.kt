package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import model.Responsavel

@Dao
interface ResponsavelDao {

    @Insert
    suspend fun inserir(responsavel: Responsavel): Long

    @Update
    suspend fun atualizar(responsavel: Responsavel)

    @Delete
    suspend fun deletar(responsavel: Responsavel)

    @Query("SELECT * FROM responsaveis")
    suspend fun listarTodos(): List<Responsavel>

    @Query("SELECT * FROM responsaveis WHERE id = :id")
    suspend fun buscarPorId(id: Int): Responsavel?

    @Query("SELECT * FROM responsaveis WHERE nome LIKE '%' || :nome || '%'")
    suspend fun buscarPorNome(nome: String): List<Responsavel>

}