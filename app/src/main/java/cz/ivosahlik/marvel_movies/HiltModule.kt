package cz.ivosahlik.marvel_movies

import cz.ivosahlik.marvel_movies.api.ApiService
import cz.ivosahlik.marvel_movies.api.MarvelApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)
}