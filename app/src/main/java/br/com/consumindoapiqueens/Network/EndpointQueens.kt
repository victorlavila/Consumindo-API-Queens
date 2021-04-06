package br.com.consumindoapiqueens.Network

import br.com.consumindoapiqueens.Model.QueensResponse
import retrofit2.http.GET

interface EndpointQueens {

    @GET("queensResponseItem")
    suspend fun getResponseQueens() : List<QueensResponse>

}