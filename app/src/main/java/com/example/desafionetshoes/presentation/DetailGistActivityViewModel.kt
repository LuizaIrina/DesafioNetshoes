package com.example.desafionetshoes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafionetshoes.data.remoteDS.Gist
import com.example.desafionetshoes.data.remoteDS.NetworkGist
import com.example.desafionetshoes.data.localDS.GistFav
import com.example.desafionetshoes.data.localDS.GistFavRoomDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
// PARTE DO DB FAVORITOS

    protected val compositeDisposable = CompositeDisposable()
    private var dataBaseInstance: GistFavRoomDatabase ?= null
    var favsList = MutableLiveData<List<GistFav>>()

    fun setInstanceOfDb(dataBaseInstance: GistFavRoomDatabase) {
        this.dataBaseInstance = dataBaseInstance
    }

    fun saveDataIntoDb(data: GistFav){
        dataBaseInstance?.gistFavDao()?.insertFavGist(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
            },{

            })
    }

    fun getFavsData(){
        dataBaseInstance?.gistFavDao()?.getAllFavGist()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                if(!it.isNullOrEmpty()){
                    favsList.postValue(it)
                }else{
                    favsList.postValue(listOf())
                }
            },{
            })
    }

    /*override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        //super.onCleared()
    }*/

    fun deleteFavs(data: GistFav) {
        dataBaseInstance?.gistFavDao()?.deleteFavGist(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                //Refresh Page data
                getFavsData()
            },{

            })

    }

// FUNCOES MINHAS
/*    fun favoritingGist(gist : Gist, context: Context){
        var gist_fav : GistFav = GistFav(gist.id,
            gist.files.map { file -> file.value.type }.toString(),
            gist.files.map { file -> file.value.filename }.toString(),
            gist.owner.login, gist.owner.avatar_url)
        DBFavGist.connectDbFavGist(context).gistFavDao().insertFavGist(gist_fav)
    }
    fun desfavoritingGist(gist : Gist, context: Context){
        var gist_fav : GistFav = GistFav(gist.id,
            gist.files.map { file -> file.value.type }.toString(),
            gist.files.map { file -> file.value.filename }.toString(),
            gist.owner.login, gist.owner.avatar_url)
        DBFavGist.connectDbFavGist(context).gistFavDao().deleteFavGist(gist_fav)
    }
    fun getListFavorites(context: Context){
        _gistFavLiveData.value = DBFavGist.connectDbFavGist(context).gistFavDao().getAllFavGist()
    }*/
}