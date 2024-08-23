package com.example.movieapp.data

import com.example.movieapp.data.dto.MovieDataDto
import com.example.movieapp.data.dto.SearchResultDto
import com.example.movieapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("/")
    suspend fun getMovieData(
        @Query("apiKey") apiKey:String= Constants.API_KEY,
        @QueryMap options:Map<String,String> = mapOf("plot" to "full","type" to "movie"),
        @Query("t") title:String
    ):MovieDataDto


    @GET("/")
    suspend fun getSearchResult(
        @Query("apiKey") apiKey:String= Constants.API_KEY,
        @Query("type") type:String="movie",
        @Query("s") search:String,
    ):SearchResultDto
}