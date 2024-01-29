package starship.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import starship.data.datasource.StarshipDataSource
import starship.data.mapper.StarshipDtoMapper
import starship.domain.model.DataResult
import starship.domain.model.Starship
import starship.domain.repository.StarShipRepository

class StarshipRepositoryImpl(
    private val networkDataSource: StarshipDataSource,
    private val starshipDtoMapper: StarshipDtoMapper
) : StarShipRepository {
    override fun getStarShips(): Flow<DataResult<List<Starship>>> = flow {

        // Omitted: Detailed error handling

        when (val result = networkDataSource.getStarshipsDto()) {
            is DataResult.Success -> {
                val starships =
                    result.data.results?.mapNotNull { starshipDtoMapper.transform(it) } ?: listOf()
                emit(DataResult.Success(starships))
            }

            is DataResult.Error -> emit(
                DataResult.Error(
                    code = result.code,
                    message = result.message
                )
            )

            is DataResult.Exception -> emit(DataResult.Exception(e = result.e))
        }
    }

}