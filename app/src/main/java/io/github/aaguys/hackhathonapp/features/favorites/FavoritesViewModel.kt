package io.github.aaguys.hackhathonapp.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.Repo

class FavoritesViewModel : ViewModel() {
    val scheduler: LiveData<List<Event>> = Repo.schedule.also { if (it.value != null) it.value!!.filter { it.isFavorite != null && it.isFavorite!! } }

    fun reload(){
        Repo.updateConfDataFromNet()
    }
}
