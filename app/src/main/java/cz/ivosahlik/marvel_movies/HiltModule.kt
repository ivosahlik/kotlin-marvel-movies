package cz.ivosahlik.marvel_movies

import android.content.Context
import androidx.room.Room
import cz.ivosahlik.marvel_movies.api.ApiService
import cz.ivosahlik.marvel_movies.api.MarvelApiRepo
import cz.ivosahlik.marvel_movies.db.CharacterDao
import cz.ivosahlik.marvel_movies.db.CollectionDb
import cz.ivosahlik.marvel_movies.db.CollectionDbRepo
import cz.ivosahlik.marvel_movies.db.CollectionDbRepoImpl
import cz.ivosahlik.marvel_movies.db.Constants
import cz.ivosahlik.marvel_movies.db.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CollectionDb::class.java, Constants.DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: CollectionDb) = collectionDb.characterDao()

    @Provides
    fun provideNoteDao(collectionDb: CollectionDb) = collectionDb.noteDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao, noteDao: NoteDao): CollectionDbRepo =
        CollectionDbRepoImpl(characterDao, noteDao)
}