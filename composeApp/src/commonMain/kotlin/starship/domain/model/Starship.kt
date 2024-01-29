package starship.domain.model

data class Starship(
    val name: String,
    val model: String,
    val crew: String? = null
)

// Omitted: More fields to display more data