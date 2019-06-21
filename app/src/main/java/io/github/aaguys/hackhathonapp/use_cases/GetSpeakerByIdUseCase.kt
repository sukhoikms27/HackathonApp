package io.github.aaguys.hackhathonapp.use_cases

import io.github.aaguys.hackhathonapp.data.Repo

class GetSpeakersByEventIdUseCase(val repo: Repo, id :String) {
    val event = repo.schedule.value?.filter { it.id == id }?.map{it.speakers}
}