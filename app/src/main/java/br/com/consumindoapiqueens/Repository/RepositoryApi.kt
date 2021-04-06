package br.com.consumindoapiqueens.Repository

import br.com.consumindoapiqueens.Model.QueensResponse
import br.com.consumindoapiqueens.Network.EndpointQueens
import br.com.consumindoapiqueens.Network.Url

class RepositoryApi {

    private var url = "http://www.nokeynoshade.party/api/queens/"
    private var service = EndpointQueens::class

    private val serviceQueen = Url(url).create(service)

    suspend fun getQueensService() : List<QueensResponse> = serviceQueen.getResponseQueens()
}