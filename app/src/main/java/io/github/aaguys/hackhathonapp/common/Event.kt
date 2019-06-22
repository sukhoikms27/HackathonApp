package io.github.aaguys.hackhathonapp.common

import org.threeten.bp.LocalDateTime


data class Event(
    val id: String,
    val title: String,
    val about: String,
    val time: LocalDateTime,
    val room: String,
    val speakers: List<Speaker>,
    val tags: List<Tag>,
    val isFavorite: Boolean?
)