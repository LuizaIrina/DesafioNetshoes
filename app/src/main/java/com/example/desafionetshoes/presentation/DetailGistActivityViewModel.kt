package com.example.desafionetshoes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafionetshoes.data.Gist
import com.example.desafionetshoes.data.GistsList
import com.example.desafionetshoes.data.NetworkGist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailGistActivityViewModel : ViewModel(){
    //QUEM VÊ É A VIEWMODEL
    private val _gistDetailLiveData = MutableLiveData<Gist>()
    //QUEM VÊ É A ACTIVITY
    val gistDetailLiveData : LiveData<Gist> = _gistDetailLiveData

    fun getGistDetail(id : String){
        NetworkGist.connectGistsAPI().detailingGists(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                //Log.e()
                // carregar a tela de "o sistema falhou"
            }
            .subscribe { resposta ->
                _gistDetailLiveData.value = resposta
                //moviesAdapter.dataset.addAll(resposta.results)
                //moviesAdapter.notifyDataSetChanged()
            }
    }



}