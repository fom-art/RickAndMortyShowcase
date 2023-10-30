package com.example.rickyandmortyshowcase.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.rickyandmortyshowcase.database.local.data.FavoriteDatabase
import com.example.rickyandmortyshowcase.database.local.domain.FavoriteDao
import com.example.rickyandmortyshowcase.database.remote.data.client.ApolloRickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersUseCase
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
    fun provideRickAndMortyShowcaseClient(apolloClient: ApolloClient): com.example.rickyandmortyshowcase.database.remote.domain.entities.client.RickAndMortyShowcaseClient {
        return ApolloRickAndMortyShowcaseClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(rickAndMortyShowcaseClient: RickAndMortyShowcaseClient): GetCharactersUseCase {
        return GetCharactersUseCase(rickAndMortyShowcaseClient)
    }
    @Provides
    @Singleton
    fun provideGetCharacterDetailsUseCase(rickAndMortyShowcaseClient: RickAndMortyShowcaseClient): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(rickAndMortyShowcaseClient)
    }
    @Provides
    @Singleton
    fun provideCharactersByNameUseCase(rickAndMortyShowcaseClient: RickAndMortyShowcaseClient): GetCharactersByNameUseCase {
        return GetCharactersByNameUseCase(rickAndMortyShowcaseClient)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
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
}