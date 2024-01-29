package starship.domain.usecase

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.every
import io.mockative.mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import starship.domain.model.DataResult
import starship.domain.model.Starship
import starship.domain.repository.StarShipRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetStarShipsUseCaseTest {

    private lateinit var getStarShipsUseCase: GetStarShipsUseCase

    @Mock
    private val starShipRepository = mock(classOf<StarShipRepository>())

    @BeforeTest
    fun setUp() {
        getStarShipsUseCase = GetStarShipsUseCase(starShipRepository)
    }

    @Test
    fun `given getStarShips of the repository is successful then build should return a Flow with Starships`() {
        val starshipsList = listOf(
            Starship("1", "Starship 1"),
            Starship("2", "Starship 2"),
            Starship("3", "Starship 3")
        )
        every {
            starShipRepository.getStarShips()
        }.returns(
            flowOf(DataResult.Success(starshipsList))
        )

        val result: Flow<DataResult<List<Starship>>> = getStarShipsUseCase.build()

        runTest {
            result.collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> assertEquals(starshipsList, dataResult.data)
                    is DataResult.Error, is DataResult.Exception -> error("Expected Success, but got Error")
                }
            }
        }
    }

    @Test
    fun `given getStarShips of the repository fails with exception then build should return a Flow with with the exception`() {
        val exception = Throwable("testError")
        every {
            starShipRepository.getStarShips()
        }.returns(
            flowOf(DataResult.Exception(exception))
        )

        val result: Flow<DataResult<List<Starship>>> = getStarShipsUseCase.build()

        runTest {
            result.collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> error("Expected Exception, but got Success")
                    is DataResult.Error -> error("Expected Exception, but got Error")
                    is DataResult.Exception -> assertEquals(exception, dataResult.e)
                }
            }
        }
    }

    @Test
    fun `given getStarShips of the repository fails with an error then build should return a Flow with with the error data`() {
        val errorCode = 400
        val errorMessage = "testMessage"

        every {
            starShipRepository.getStarShips()
        }.returns(
            flowOf(DataResult.Error(code = errorCode, message = errorMessage))
        )

        val result: Flow<DataResult<List<Starship>>> = getStarShipsUseCase.build()

        runTest {
            result.collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> error("Expected Error, but got Success")
                    is DataResult.Error -> {
                        assertEquals(errorCode, dataResult.code)
                        assertEquals(errorMessage, dataResult.message)
                    }

                    is DataResult.Exception -> error("Expected Exception, but got Error")
                }
            }
        }
    }
}
