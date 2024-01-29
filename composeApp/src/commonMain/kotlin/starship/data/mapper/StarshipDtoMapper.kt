package starship.data.mapper

import starship.data.model.Result
import starship.domain.model.Starship
import util.checkNotNullOrEmpty

class StarshipDtoMapper {

    fun transform(result: Result): Starship? {
        return try {
            checkNotNullOrEmpty(result.name)
            checkNotNullOrEmpty(result.model)

            Starship(
                name = result.name!!,
                model = result.model!!,
                crew = result.crew
            )
        } catch (e: IllegalStateException) {
            null
        }
    }

}