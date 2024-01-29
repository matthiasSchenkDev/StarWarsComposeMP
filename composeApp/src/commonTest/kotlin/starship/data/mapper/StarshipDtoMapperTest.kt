package starship.data.mapper

import starship.data.model.Result
import starship.domain.model.Starship
import kotlin.test.Test
import kotlin.test.assertEquals

class StarshipDtoMapperTest {

    private val starshipDtoMapper = StarshipDtoMapper()

    @Test
    fun `given a valid Result transform should return a Starship`() {
        val validResult = Result("StarshipName", "StarshipModel", crew = "42")
        val result = starshipDtoMapper.transform(validResult)

        assertEquals(
            Starship(
                name = "StarshipName",
                model = "StarshipModel",
                crew = "42"
            ),
            result
        )
    }

    @Test
    fun `given a Result with invalid name transform should return null`() {
        val invalidResult = Result(null, "model", "42")
        val result = starshipDtoMapper.transform(invalidResult)
        assertEquals(null, result)
    }

    @Test
    fun `given a result with invalid model transform should return null`() {
        val invalidResult = Result("name", null, "42")
        val result = starshipDtoMapper.transform(invalidResult)
        assertEquals(null, result)
    }
}
