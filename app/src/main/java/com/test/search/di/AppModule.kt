package com.test.search.di

import com.test.search.presentation.networking.helpers.BaseApi
import org.koin.dsl.module

val appModule =  module {
    single { BaseApi() }
}