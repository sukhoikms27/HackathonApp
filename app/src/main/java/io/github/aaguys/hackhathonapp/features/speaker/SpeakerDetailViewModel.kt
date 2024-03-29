package io.github.aaguys.hackhathonapp.features.speaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.data.Repo
import io.github.aaguys.hackhathonapp.use_cases.GetEventByIdUseCase

class SpeakerDetailViewModel : ViewModel() {

    val scheduler: LiveData<List<Event>> = Repo.schedule
    fun getEvent(eventId:String) = GetEventByIdUseCase(Repo, eventId).event

    fun reload(){
        Repo.updateConfDataFromNet()
    }
}