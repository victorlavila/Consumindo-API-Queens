package br.com.consumindoapiqueens.Network

import br.com.consumindoapiqueens.Model.QueensResponse
import br.com.consumindoapiqueens.Model.QueensResponseItem
import retrofit2.http.GET

interface EndpointQueens {

    @GET("all")
    suspend fun getResponseQueens() : List<QueensResponseItem>

}