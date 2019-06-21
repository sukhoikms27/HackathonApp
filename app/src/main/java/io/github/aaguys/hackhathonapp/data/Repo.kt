package io.github.aaguys.hackhathonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.common.Speaker
import io.github.aaguys.hackhathonapp.data.db.DbDummy
import io.github.aaguys.hackhathonapp.data.network.NetworkDummy

class Repo {
    private val netDP = NetworkDummy
    private val db = DbDummy

    val schedule: LiveData<List<Event>>
        get() {
            return if (!db.schedule.value.isNullOrEmpty()) db.schedule
            else Transformations.map(netDP.confData) { it.events }
        }

    val favorites: LiveData<List<Event>>
        get() = Transformations.map(netDP.confData) { it.events }

    val aboutConf: LiveData<AboutConfData>
        get() {
            return if (db.aboutConf.value != null) db.aboutConf
            else Transformations.map(netDP.confData) { it.aboutConf }
        }


    fun updateSchedule() {
        Transformations.map(netDP.confData) { it.events }?.let {
            it.value?.let { value -> db.addSchedule(value) }
        }
    }

    fun addToFavorites(event: Event) = db.addToFavorites(event)

    fun removeFromFavorites(event: Event) = db.removeFromFavorites(event)


}