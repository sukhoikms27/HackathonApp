package io.github.aaguys.hackhathonapp.common

data class Speaker(
    val id: String,
    val name: String,
    val photoUrl: String,
    val job: String,
    val info: String,
    val links: Links?
)

data class Links(
    val twitter: String? = null,
    val telegram: String? = null,
    val site: String? = null,
    val email: String? = null
)