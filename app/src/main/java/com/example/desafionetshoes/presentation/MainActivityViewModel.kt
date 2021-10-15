package com.example.desafionetshoes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafionetshoes.data.remoteDS.Gist
import com.example.desafionetshoes.data.remoteDS.NetworkGist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    //QUEM VÊ É A VIEWMODEL
    private val _gistsLiveData = MutableLiveData<List<Gist>>()
    //QUEM VÊ É A ACTIVITY
    val gistsLiveData : LiveData<List<Gist>> = _gistsLiveData

    //private val fetchCharactersUseCase = FetchCharactersUseCase()

    fun getGists(){

        NetworkGist.connectGistsAPI().listingGists()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                //Log.e()
                // carregar a tela de "o sistema falhou"
            }
            .subscribe { resposta ->
                _gistsLiveData.value = resposta
                //moviesAdapter.dataset.addAll(resposta.results)
                //moviesAdapter.notifyDataSetChanged()
            }
    }
}