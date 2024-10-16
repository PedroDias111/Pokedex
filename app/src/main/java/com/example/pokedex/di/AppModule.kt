package com.example.pokedex.di

import android.app.Application
import androidx.room.Room
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.remote.PokeAPI
import com.example.pokedex.data.remote.PokeAPI.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokeAPI(): PokeAPI {
        // Configure OkHttpClient with logging
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        // Configure Json with ignoreUnknownKeys
        val json = Json {
            ignoreUnknownKeys = true // Ignore unknown keys in the JSON response
        }

        // Build Retrofit instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(okHttpClient)
            .build()
            .create(PokeAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesPokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            "pokemondb.db"
        ).build()
    }

}