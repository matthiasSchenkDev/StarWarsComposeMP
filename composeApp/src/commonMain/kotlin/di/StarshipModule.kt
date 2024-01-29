package di

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import starship.data.mapper.StarshipDtoMapper
import starship.data.repository.StarshipRepositoryImpl
import starship.domain.repository.StarShipRepository
import starship.domain.usecase.GetStarShipsUseCase
import starship.presentation.viewmodel.StarshipViewModel

val starshipModule = module {
    single<StarShipRepository> {
        StarshipRepositoryImpl(
            networkDataSource = get(qualifier(name = Qualifiers.NETWORK)),
            starshipDtoMapper = StarshipDtoMapper()
        )
    }
    factory { GetStarShipsUseCase(starShipRepository = get()) }
    single { StarshipViewModel(getStarShipsUseCase = get()) }
}