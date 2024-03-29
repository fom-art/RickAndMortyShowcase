package com.example.rickyandmortyshowcase.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.rickyandmortyshowcase.data.CharactersRepository
import com.example.rickyandmortyshowcase.data.FavoritesRepository
import com.example.rickyandmortyshowcase.data.LocalFavouritesDataSource
import com.example.rickyandmortyshowcase.data.RemoteCharactersDataSource
import com.example.rickyandmortyshowcase.data.local.data.FavoriteDatabase
import com.example.rickyandmortyshowcase.data.local.data.FavoriteDao
import com.example.rickyandmortyshowcase.data.local.domain.FavoritesOfflineRepository
import com.example.rickyandmortyshowcase.data.remote.data.client.ApolloRickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.data.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.domain.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.domain.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.domain.GetCharactersUseCase
import com.example.rickyandmortyshowcase.domain.GetFavouritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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
    fun provideRemoteCharactersDataSource(rickAndMortyShowcaseClient: RickAndMortyShowcaseClient): RemoteCharactersDataSource {
        return RemoteCharactersDataSource(rickAndMortyShowcaseClient)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(remoteCharactersDataSource: RemoteCharactersDataSource): CharactersRepository {
        return CharactersRepository(remoteCharactersDataSource)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsUseCase(charactersRepository: CharactersRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(charactersRepository)
    }

    @Provides
    @Singleton
    fun provideCharactersByNameUseCase(charactersRepository: CharactersRepository): GetCharactersByNameUseCase {
        return GetCharactersByNameUseCase(charactersRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository)
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
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideLocalFavouritesDataSource(favoriteDao: FavoriteDao): LocalFavouritesDataSource {
        return LocalFavouritesDataSource(favoriteDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(localFavouritesDataSource: LocalFavouritesDataSource): FavoritesRepository {
        return FavoritesRepository(localFavouritesDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(favoritesRepository: FavoritesRepository): GetFavouritesUseCase {
        return GetFavouritesUseCase(favoritesRepository)
    }
}