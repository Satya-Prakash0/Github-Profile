package com.satya.profilesearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}