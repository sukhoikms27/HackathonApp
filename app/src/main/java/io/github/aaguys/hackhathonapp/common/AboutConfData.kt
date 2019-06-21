package io.github.aaguys.hackhathonapp.common

data class AboutConfData(
    val name:String,
    val year: Int,
    val site: String,
    val imageUrl: String,
    val about: String,
    val rules: String,
    val coordinates: String,
    val contacts: List<Contact>
)

data class Contact (
    val name: String,
    val link: String
)
