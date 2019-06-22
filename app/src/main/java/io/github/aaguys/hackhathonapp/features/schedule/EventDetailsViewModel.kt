package io.github.aaguys.hackhathonapp.features.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.Repo

class EventDetailsViewModel : ViewModel() {
    val scheduler: LiveData<List<Event>> = Repo.schedule
}
