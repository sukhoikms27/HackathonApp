package io.github.aaguys.hackhathonapp.common

data class ConfData (
    val events:List<Event>,
    val speakers: List<Speaker>,
    val aboutConf:AboutConfData
)