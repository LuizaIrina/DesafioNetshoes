package com.example.desafionetshoes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafionetshoes.data.Gist
import com.example.desafionetshoes.data.GistsList
import com.example.desafionetshoes.data.NetworkGist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    //QUEM VÊ É A VIEWMODEL
    private val _gistsLiveData = MutableLiveData<List<Gist>>()
    //QUEM VÊ É A ACTIVITY
    val gistsLiveData : LiveData<List<Gist>> = _gistsLiveData

    //private val fetchCharactersUseCase = FetchCharactersUseCase()

    fun getGists(){
        /*var listTest = mutableListOf<GistsList>()
        val gist1 : GistsList = GistsList("amanda","www","xml")
        val gist2 : GistsList = GistsList("vitor","www","py")
        val gist3 : GistsList = GistsList("fernando","www","readme")
        listTest.add(gist1);listTest.add(gist2);listTest.add(gist3)
        listTest.add(GistsList("roberta","www","c"))
        _gistsLiveData.value = listTest*/

        NetworkGist.getGist().listingGists()
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