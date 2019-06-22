package io.github.aaguys.hackhathonapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private fun retrofit(): Retrofit = Retrofit.Builder()
        //.client(OkHttpClient().newBuilder().build())
        .baseUrl("https://api.myjson.com/bins/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val devFestApi: NetworkFetcherApi = retrofit().create(NetworkFetcherApi::class.java)
}