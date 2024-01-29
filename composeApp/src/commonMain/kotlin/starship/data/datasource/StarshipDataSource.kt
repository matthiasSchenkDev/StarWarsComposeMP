package starship.data.datasource

import starship.data.model.StarshipsDto
import starship.domain.model.DataResult

interface StarshipDataSource {

    suspend fun getStarshipsDto(): DataResult<StarshipsDto>

}