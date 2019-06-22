package io.github.aaguys.hackhathonapp.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Event

object DbDummy:DbProvider {
    override val favorites: LiveData<List<Event>>
        get() = MutableLiveData()

    private val _schedule = MutableLiveData<List<Event>>()

    override val schedule: LiveData<List<Event>>
        get() = _schedule
    init {
        _schedule.value = null
    }
    private val _aboutConf = MutableLiveData<List<Event>>()
    override val aboutConf: LiveData<AboutConfData>
        get() = MutableLiveData()

    override fun addSchedule(schedule: List<Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToFavorites(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavorites(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateAboutConf(aboutConfData: AboutConfData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}