package di

import di.Endpoints.SWAPI_BASE
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import starship.data.api.StarshipApi
import starship.data.datasource.StarshipDataSource

object Endpoints {
    const val SWAPI_BASE = "https://swapi.dev/api"
}

val networkModule = module {
    single {
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
    }

    single<StarshipDataSource>(
        qualifier = qualifier(name = Qualifiers.NETWORK)
    ) {
        StarshipApi(baseUrl = SWAPI_BASE, httpClient = get())
    }
}