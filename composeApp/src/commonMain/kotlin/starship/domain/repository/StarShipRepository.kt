package starship.domain.repository

import kotlinx.coroutines.flow.Flow
import starship.domain.model.DataResult
import starship.domain.model.Starship

interface StarShipRepository {

    fun getStarShips(): Flow<DataResult<List<Starship>>>

}