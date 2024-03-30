package com.example.rickyandmortyshowcase.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.rickyandmortyshowcase.characters.data.CharactersRepository
import com.example.rickyandmortyshowcase.characters.data.network.RemoteCharactersDataSource
import com.example.rickyandmortyshowcase.characters.data.local.FavoriteDatabase
import com.example.rickyandmortyshowcase.characters.data.local.FavoritesDao
import com.example.rickyandmortyshowcase.characters.data.network.ApolloRickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.characters.data.network.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.characters.domain.DeleteCharacterFromFavouritesUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetCharactersUseCase
import com.example.rickyandmortyshowcase.characters.domain.GetFavouritesUseCase
import com.example.rickyandmortyshowcase.characters.domain.UpsertCharacterToFavouritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://rickandmortyapi.com/graphql").build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyShowcaseClient(apolloClient: ApolloClient): RickAndMortyShowcaseClient {
        return ApolloRickAndMortyShowcaseClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            "Favorites"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRemoteCharactersDataSource(rickAndMortyShowcaseClient: RickAndMortyShowcaseClient): RemoteCharactersDataSource {
        return RemoteCharactersDataSource(rickAndMortyShowcaseClient)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoritesDao {
        return favoriteDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(remoteCharactersDataSource: RemoteCharactersDataSource, favouritesDao: FavoritesDao): CharactersRepository {
        return CharactersRepository(remoteCharactersDataSource, favouritesDao)
    }

    @Provides
    fun provideGetCharacterDetailsUseCase(charactersRepository: CharactersRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(charactersRepository)
    }

    @Provides
    fun provideCharactersByNameUseCase(charactersRepository: CharactersRepository): GetCharactersByNameUseCase {
        return GetCharactersByNameUseCase(charactersRepository)
    }

    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository)
    }

    @Provides
    fun provideGetFavouritesUseCase(charactersRepository: CharactersRepository): GetFavouritesUseCase {
        return GetFavouritesUseCase(charactersRepository)
    }

    @Provides
    fun provideDeleteCharacterFromFavouritesUseCase(charactersRepository: CharactersRepository): DeleteCharacterFromFavouritesUseCase {
        return DeleteCharacterFromFavouritesUseCase(charactersRepository)
    }

    @Provides
    fun provideUpsertCharacterToFavouritesUseCase(charactersRepository: CharactersRepository): UpsertCharacterToFavouritesUseCase {
        return UpsertCharacterToFavouritesUseCase(charactersRepository)
    }
}