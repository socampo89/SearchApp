package com.test.search.di

import com.test.search.data.repository.SearchRepositoryImpl
import com.test.search.domain.repository.SearchRepository
import com.test.search.domain.services.SearchApiService
import com.test.search.domain.use_cases.search.SearchUseCase
import com.test.search.domain.use_cases.search.SearchUseCases
import com.test.search.presentation.viewModels.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ext.getFullName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val searchModule = module {
    factory(named(SearchApiService::class.getFullName())) { getRetrofit("https://api.mercadolibre.com/").create(SearchApiService::class.java) }
    factory<SearchRepository>{ SearchRepositoryImpl(get(named(SearchApiService::class.getFullName()))) }
    factory { SearchUseCases(SearchUseCase(get())) }
    viewModel { SearchViewModel(get()) }
}

fun getRetrofit(baseUrl : String) : Retrofit {
   return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}