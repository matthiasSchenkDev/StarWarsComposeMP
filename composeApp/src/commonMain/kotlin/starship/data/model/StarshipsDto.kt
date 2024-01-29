package starship.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarshipsDto(
    val count: Long? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Result>? = null
)

@Serializable
data class Result(
    val name: String? = null,
    val model: String? = null,
    val manufacturer: String? = null,

    @SerialName("cost_in_credits")
    val costInCredits: String? = null,

    val length: String? = null,

    @SerialName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String? = null,

    val crew: String? = null,
    val passengers: String? = null,

    @SerialName("cargo_capacity")
    val cargoCapacity: String? = null,

    val consumables: String? = null,

    @SerialName("hyperdrive_rating")
    val hyperdriveRating: String? = null,

    @SerialName("MGLT")
    val mglt: String? = null,

    @SerialName("starship_class")
    val starshipClass: String? = null,

    val pilots: List<String>? = null,
    val films: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
)
