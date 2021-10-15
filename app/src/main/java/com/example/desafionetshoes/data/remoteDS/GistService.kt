package com.example.desafionetshoes.data.remoteDS

import com.example.desafionetshoes.data.remoteDS.Gist
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GistService {
    @GET("gists")
    fun listingGists(): Observable<List<Gist>>

    @GET("gists/{gist_id}")
    fun detailingGists(@Path("gist_id") id: String): Observable<Gist>

}