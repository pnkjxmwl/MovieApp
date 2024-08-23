package com.example.movieapp.di

import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.repository.RemoteDataRepositoryImpl
import com.example.movieapp.domain.repository.RemoteDataRepository
import com.example.movieapp.domain.use_cases.GetMovieDataUseCase
import com.example.movieapp.domain.use_cases.GetSearchResultUseCase
import com.example.movieapp.domain.use_cases.UseCases
import com.example.movieapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMoshi(): Moshi =Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(moshi: Moshi): MovieApi{
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieApi): RemoteDataRepository{
        return  RemoteDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RemoteDataRepository) : UseCases{
        return UseCases(
            getSearchResultUseCase = GetSearchResultUseCase(repository),
            getMovieDataUseCase = GetMovieDataUseCase(repository)
        )
    }

}