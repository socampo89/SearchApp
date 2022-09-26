package com.test.search.domain.use_cases

import com.test.search.presentation.networking.helpers.Resource

abstract class BaseUseCase<T, in Params> {
    abstract suspend fun invoke(params: Params? = null): Resource<T>
}