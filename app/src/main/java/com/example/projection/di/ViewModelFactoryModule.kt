package com.example.projection.di

import com.example.projection.view.screen.profile.ProfileIndexViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryModule {

    fun profileIndexViewModelFactory(): ProfileIndexViewModel.Factory
}
