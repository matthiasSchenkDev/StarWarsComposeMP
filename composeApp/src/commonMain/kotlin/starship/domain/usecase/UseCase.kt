package starship.domain.usecase

import kotlinx.coroutines.flow.Flow
import starship.domain.model.DataResult

interface UseCase<T : Any> {
    fun build(params: UseCaseParams? = null): Flow<DataResult<T>>

}