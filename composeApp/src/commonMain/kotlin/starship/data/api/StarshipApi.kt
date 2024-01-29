package starship.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import starship.data.datasource.StarshipDataSource
import starship.data.model.StarshipsDto
import starship.domain.model.DataResult

class StarshipApi(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : StarshipDataSource {
    override suspend fun getStarshipsDto(): DataResult<StarshipsDto> {
        return try {
            val response = httpClient.get("$baseUrl/starships")
            return if (response.status == HttpStatusCode.OK) {
                val starshipsDto = response.body<StarshipsDto>()
                DataResult.Success(starshipsDto)
            } else DataResult.Error(
                code = response.status.value,
                message = response.status.description
            )
        } catch (e: Throwable) {
            DataResult.Exception(e)
        }
    }
}