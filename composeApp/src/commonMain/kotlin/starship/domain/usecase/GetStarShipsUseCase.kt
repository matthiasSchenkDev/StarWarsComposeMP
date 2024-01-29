package starship.domain.usecase

import kotlinx.coroutines.flow.Flow
import starship.domain.model.DataResult
import starship.domain.model.Starship
import starship.domain.repository.StarShipRepository

class GetStarShipsUseCase(
    private val starShipRepository: StarShipRepository
) : UseCase<List<Starship>> {

    override fun build(params: UseCaseParams?): Flow<DataResult<List<Starship>>> {
        return starShipRepository.getStarShips()
    }

}