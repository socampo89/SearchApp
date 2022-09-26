package com.test.search.presentation

import android.app.Application
import com.test.search.di.KoinDI

class App : Application (){
    override fun onCreate() {
        super.onCreate()
        setupInjection()
    }

    private fun setupInjection() {
        KoinDI(this).init()
    }
}