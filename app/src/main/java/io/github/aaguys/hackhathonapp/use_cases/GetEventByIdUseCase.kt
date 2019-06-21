package io.github.aaguys.hackhathonapp.use_cases

import io.github.aaguys.hackhathonapp.data.Repo

class GetEventByIdUseCase(repo: Repo, id: String) {
    val event = repo.schedule.value?.find { it.id == id }
    val events = repo.schedule.value
}