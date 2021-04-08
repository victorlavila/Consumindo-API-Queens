package br.com.consumindoapiqueens.ViewModel

import android.net.LinkProperties
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.consumindoapiqueens.Model.QueensResponse
import br.com.consumindoapiqueens.Model.QueensResponseItem
import br.com.consumindoapiqueens.Repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelQueens : ViewModel() {

    val listMutableQueens = MutableLiveData<List<QueensResponseItem>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

    init {
        getAllQueens()
    }

    private fun getAllQueens() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getQueensService().let {
                listMutableQueens.postValue(it)
                loading.postValue(false)
            }
        }catch (error : Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique a sua conexão")
        }
    }

}