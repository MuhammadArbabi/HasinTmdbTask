package com.della.hassintmdbtask.di

import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.repository.PopularMovieRepositoryImpl
import com.della.hassintmdbtask.domain.PopularMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePopularMovieRepository(apiService: ApiService):PopularMovieRepository = PopularMovieRepositoryImpl(apiService)
}