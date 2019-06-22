package io.github.aaguys.hackhathonapp.data.network.entities


import com.google.gson.annotations.SerializedName

data class ConfDataResponse(
    @SerializedName("speakers")
    val speakers: List<Speaker>,
    @SerializedName("events")
    val events: List<Event>,
    @SerializedName("aboutConf")
    val aboutConf: AboutConf
) {
    data class Event(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("about")
        val about: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("room")
        val room: String,
        @SerializedName("speakers")
        val speakers: List<String>,
        @SerializedName("tags")
        val tags: List<Tag>
    ) {
        data class Tag(
            @SerializedName("name")
            val name: String,
            @SerializedName("color")
            val color: Int
        )
    }

    data class AboutConf(
        @SerializedName("name")
        val name: String,
        @SerializedName("year")
        val year: Int,
        @SerializedName("site")
        val site: String,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("about")
        val about: String,
        @SerializedName("rules")
        val rules: String,
        @SerializedName("coordinates")
        val coordinates: String,
        @SerializedName("contacts")
        val contacts: List<Contact>
    ) {
        data class Contact(
            @SerializedName("name")
            val name: String,
            @SerializedName("link")
            val link: String
        )
    }

    data class Speaker(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("photoUrl")
        val photoUrl: String,
        @SerializedName("job")
        val job: String,
        @SerializedName("info")
        val info: String,
        @SerializedName("links")
        val links: Links
    ) {
        data class Links(
            @SerializedName("twitter")
            val twitter: String,
            @SerializedName("telegram")
            val telegram: String,
            @SerializedName("site")
            val site: String,
            @SerializedName("email")
            val email: String
        )
    }
}