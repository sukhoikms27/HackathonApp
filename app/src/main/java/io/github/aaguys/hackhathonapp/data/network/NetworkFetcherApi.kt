package io.github.aaguys.hackhathonapp.data.network

import io.github.aaguys.hackhathonapp.data.network.entities.ConfDataResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NetworkFetcherApi {
    @GET("1dxlih")
    fun getConfDataAsync(): Deferred<Response<ConfDataResponse>>
}