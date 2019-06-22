package io.github.aaguys.hackhathonapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.aaguys.hackhathonapp.data.network.entities.ConfDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object NetworkDataProvider {
    private val service = ApiFactory.devFestApi
    private val _devFestDataFromNet = MutableLiveData<ConfDataResponse>()
    val dataFromNet:LiveData<ConfDataResponse> = _devFestDataFromNet

    fun updateConfDataFromNet(){
        GlobalScope.launch(Dispatchers.Main)
        {
            val postRequest = service.getConfDataAsync() // Making Network call
            try {
                val response = postRequest.await()
                if (response.isSuccessful) {
                    _devFestDataFromNet.postValue(response.body())
                } else {
                    Log.d("NetworkDataProvider ", response.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.d("NetworkDataProvider ", e.toString())
            }
        }
    }


}