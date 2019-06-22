package io.github.aaguys.hackhathonapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.converters.toConfData
import io.github.aaguys.hackhathonapp.data.db.DbDummy
import io.github.aaguys.hackhathonapp.data.network.NetworkDataProvider

object Repo {
    private val netDP = NetworkDataProvider
    private val db = DbDummy

    val schedule: LiveData<List<Event>>
        get() {
val r =Transformations.map(netDP.dataFromNet) { it.toConfData().events }
//            val r =if (!db.schedule.value.isNullOrEmpty()) db.schedule
//            else Transformations.map(netDP.dataFromNet) { it.toConfData().events }
           Log.d("1233","${r.value?.size}")
            return r


        }

    val favorites: LiveData<List<Event>>
        get() = Transformations.map(netDP.dataFromNet) { it.toConfData().events }

    val aboutConf: LiveData<AboutConfData>
        get() {
            return if (db.aboutConf.value != null) db.aboutConf
            else Transformations.map(netDP.dataFromNet) { it.toConfData().aboutConf }
        }


    fun updateScheduleInDb() {
        netDP.dataFromNet.value?.toConfData()?.events?.let { db.addSchedule(it) }
    }

    fun updateConfDataFromNet(){
        netDP.updateConfDataFromNet()
    }


    fun addToFavorites(event: Event) = db.addToFavorites(event)

    fun removeFromFavorites(event: Event) = db.removeFromFavorites(event)


}