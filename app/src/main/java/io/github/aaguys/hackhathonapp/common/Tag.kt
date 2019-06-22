package io.github.aaguys.hackhathonapp.common

data class Tag(
    val name: String,
    val color: Int
)

object ColorProvider {
    fun getColor(name: String): Int {
        return when (name.capitalize()) {
            "ML" -> (0xFF555E7B).toInt()
            "Database" -> (0xFFB7D968).toInt()
            "PostgreSQL" -> (0xFFB576AD).toInt()
            "Opensource" -> (0xFFE04644).toInt()
            "Distributed" -> (0xFFFDE47F).toInt()
            "Openmind" -> (0xFF7CCCE5).toInt()
            "Goblin" -> (0xFF7EC601).toInt()
            "Frontend" -> (0xFFEA23BF).toInt()
            "Q&A" -> (0xFF018C3D).toInt()
            "Casestudy" -> (0xFF7CEA8E).toInt()
            "Xamarin" -> (0xFFA68EDB).toInt()
            "Architecture" -> (0xFF1C0066).toInt()
            "UTC" -> (0xFF3BD498).toInt()
            "Management" -> (0xFFE8AA37).toInt()
            "Engineer" -> (0xFF64F47C).toInt()

            else -> (0xFFFFFFFF).toInt()
        }
    }
}

