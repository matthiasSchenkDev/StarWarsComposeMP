package starship.presentation.state

import starship.domain.model.Starship

data class StarshipsState(
    val starships: List<Starship> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)