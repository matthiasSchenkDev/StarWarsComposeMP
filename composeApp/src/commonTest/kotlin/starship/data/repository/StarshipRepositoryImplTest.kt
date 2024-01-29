package starship.data.repository

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import starship.data.datasource.StarshipDataSource
import starship.data.mapper.StarshipDtoMapper
import starship.data.model.Result
import starship.data.model.StarshipsDto
import starship.domain.model.DataResult
import starship.domain.repository.StarShipRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StarshipRepositoryTest {

    @Mock
    private val starshipApi = mock(classOf<StarshipDataSource>())

    private lateinit var starshipDtoMapper: StarshipDtoMapper
    private lateinit var starshipRepository: StarShipRepository

    @BeforeTest
    fun setUp() {
        starshipDtoMapper = StarshipDtoMapper()
        starshipRepository = StarshipRepositoryImpl(starshipApi, starshipDtoMapper)
    }

    @Test
    fun `given getStarshipsDto of the datasource is successful then getStarShips should return a DataResult flow with a list of Starships`() =
        runTest {

            val resultList = listOf(
                Result(
                    name = "testName1",
                    model = "testModel1"
                ),
                Result(
                    name = "testName2",
                    model = "testModel2"
                )
            )

            coEvery { starshipApi.getStarshipsDto() }.returns(
                DataResult.Success(StarshipsDto(results = resultList))
            )

            val result = starshipRepository.getStarShips()

            result.collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> assertEquals(dataResult.data.size, resultList.size)
                    is DataResult.Error -> error("Expected Success, but got Error")
                    is DataResult.Exception -> error("Expected Success, but got Exception")
                }
            }
        }

    @Test
    fun `given getStarshipsDto of the datasource returns an exception then getStarShips should return a DataResult flow with the exception`() {
    }

    @Test
    fun `given getStarshipsDto of the datasource returns an error then getStarShips should return a DataResult flow with the error`() {
    }
}
