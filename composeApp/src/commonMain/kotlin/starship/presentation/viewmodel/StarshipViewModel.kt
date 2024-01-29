package starship.presentation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import starship.domain.model.DataResult
import starship.domain.usecase.GetStarShipsUseCase
import starship.presentation.state.StarshipsState

class StarshipViewModel(
    private val getStarShipsUseCase: GetStarShipsUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _starshipsState = MutableStateFlow(StarshipsState())
    val starshipsState = _starshipsState.asStateFlow()

    fun getStarships() {
        viewModelScope.launch(ioDispatcher) {
            _starshipsState.update {
                it.copy(isLoading = true, isError = false)
            }
            getStarShipsUseCase.build().collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> {
                        _starshipsState.update {
                            // Omitted: Use of 'StarshipsEntity' + 'StarshipsEntityMapper' classes to potentially prepare 'Starship' data better for UI
                            it.copy(
                                starships = dataResult.data,
                                isLoading = false,
                                isError = false
                            )
                        }
                    }

                    is DataResult.Error, is DataResult.Exception ->
                        _starshipsState.update {
                            it.copy(isLoading = false, isError = true)
                        }
                }
            }
        }
    }

}