package io.github.aaguys.hackhathonapp.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.aaguys.hackhathonapp.common.AboutConfData
import io.github.aaguys.hackhathonapp.common.Event
import io.github.aaguys.hackhathonapp.common.Speaker
import io.github.aaguys.hackhathonapp.common.Tag
import org.threeten.bp.LocalTime

object DbDummy:DbProvider {
    override val favorites: LiveData<List<Event>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private val _schedule = MutableLiveData<List<Event>>()

    override val schedule: LiveData<List<Event>>
        get() = _schedule
    init {
        _schedule.value = listOf(
            Event(
                id = "1",
                title = "CatBoost — библиотека машинного обучения для решения практических задач с использованием больших данных",
                about = "blah-blah",
                time = LocalTime.of(18, 30),
                tags = listOf(Tag("blah", 123)),
                speakers = listOf(
                    Speaker(
                        id = "1",
                        name = "Василий Ершов",
                        photoUrl = "",
                        job = "job",
                        info = "simple info",
                        links = null
                    )
                ),
                isFavorite = false,
                room = "room 1"

            ), Event(
                id = "2",
                title = "Title of Event",
                about = "blah-blah",
                time = LocalTime.of(18, 30),
                tags = listOf(Tag("blah", 123)),
                speakers = listOf(
                    Speaker(
                        id = "1",
                        name = "SpeakerName",
                        photoUrl = "",
                        job = "job",
                        info = "simple info",
                        links = null
                    )
                ),
                isFavorite = false,
                room = "room 1"

            ), Event(
                id = "3",
                title = "Title of Event",
                about = "blah-blah",
                time = LocalTime.of(18, 30),
                tags = listOf(Tag("blah", 123)),
                speakers = listOf(
                    Speaker(
                        id = "1",
                        name = "SpeakerName",
                        photoUrl = "",
                        job = "job",
                        info = "simple info",
                        links = null
                    )
                ),
                isFavorite = false,
                room = "room 1"

            )
        )
    }
    override val aboutConf: LiveData<AboutConfData>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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