package io.github.aaguys.hackhathonapp.use_cases

import androidx.lifecycle.Transformations
import io.github.aaguys.hackhathonapp.common.Tag
import io.github.aaguys.hackhathonapp.data.Repo

class GetFiltredScheduleUseCase(val repo: Repo, tags: List<Tag>) {
    private val tagsNames = tags.mapTo(HashSet(), Tag::name)
    val schedule = Transformations.map(repo.schedule) { events ->
        events.filter { it.tags.any { tag -> tag.name in tagsNames } }
    }
}