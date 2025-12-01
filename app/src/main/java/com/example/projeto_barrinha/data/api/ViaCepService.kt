package com.example.projeto_barrinha.data.api

import com.example.projeto_barrinha.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {
    @GET("{cep}/json/")
    suspend fun buscarEndereco(@Path("cep") cep: String): Endereco
}