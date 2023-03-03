package com.gentalha.characters.di

import com.gentalha.characters.remote.service.CharacterApi
import com.gentalha.config.network.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterModule {
    @Provides
    @Singleton
    fun provideCharacterService() =
        ServiceBuilder().invoke<CharacterApi>("https://swapi.dev/api/")
}