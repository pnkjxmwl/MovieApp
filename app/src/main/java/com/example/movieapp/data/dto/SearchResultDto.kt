package com.example.movieapp.data.dto


import com.example.movieapp.domain.SearchResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultDto(
    @Json(name = "Search")
    val movies: List<Movie>,
    @Json(name = "totalResults")
    val totalResults: String,
    @Json(name = "Response")
    val response: String
)

fun SearchResultDto.toSearchResult():SearchResult{
    return SearchResult(movies=movies)
}