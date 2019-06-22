package io.github.aaguys.hackhathonapp.data.converters

import io.github.aaguys.hackhathonapp.common.*
import io.github.aaguys.hackhathonapp.data.network.entities.ConfDataResponse
import org.threeten.bp.LocalDateTime

fun ConfDataResponse.toConfData(): ConfData {
    val speakersList = speakers.map {
        Speaker(
            it.id,
            it.name,
            it.photoUrl,
            it.job,
            it.info,
            it.links?.let { link ->
                Links(
                    link.twitter,
                    link.telegram,
                    link.site,
                    link.email
                )
            }

        )
    }

    return ConfData(
        events = events.map {
            Event(
                it.id,
                it.title,
                it.about,
                LocalDateTime.parse(it.time),
                it.room,
                it.speakers.map { speakerId ->
                    speakersList.first { sp -> sp.id == speakerId }
                },
                it.tags.map { tag ->
                    Tag(tag.name, tag.color)
                },
                null
            )
        },
        speakers = speakersList,
        aboutConf = AboutConfData(
            aboutConf.name,
            aboutConf.year,
            aboutConf.site,
            aboutConf.imageUrl,
            aboutConf.about,
            aboutConf.rules,
            aboutConf.coordinates,
            aboutConf.contacts.map { Contact(it.name, it.link) }
        )
    )
}
