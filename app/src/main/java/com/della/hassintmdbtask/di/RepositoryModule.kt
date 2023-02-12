package com.della.hassintmdbtask.di

import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.repository.MovieDetailRepositoryImpl
import com.della.hassintmdbtask.data.repository.PopularMovieRepositoryImpl
import com.della.hassintmdbtask.domain.MovieDetailRepository
import com.della.hassintmdbtask.domain.PopularMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providePopularMovieRepository(apiService: ApiService):PopularMovieRepository = PopularMovieRepositoryImpl(apiService)


    @Provides
    fun provideDetailMovieRepository(apiService: ApiService): MovieDetailRepository = MovieDetailRepositoryImpl(apiService)
}