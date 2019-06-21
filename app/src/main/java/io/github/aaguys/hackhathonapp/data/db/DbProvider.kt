package io.github.aaguys.hackhathonapp.data.db

import androidx.lifecycle.LiveData
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Event

interface DbProvider {
    val favorites:LiveData<List<Event>>
    val schedule:LiveData<List<Event>>
    val aboutConf:LiveData<AboutConfData>
    fun addSchedule(schedule :List<Event>)
    fun addToFavorites(event: Event)
    fun removeFromFavorites(event: Event)
    fun updateAboutConf(aboutConfData :AboutConfData)
}