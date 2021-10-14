package com.example.desafionetshoes.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistService {
    @GET("gists")
    fun listingGists(): Observable<List<Gist>>

    @GET("gists/{gist_id}")
    fun detailingGists(@Path("gist_id") id: String): Observable<Gist>

}