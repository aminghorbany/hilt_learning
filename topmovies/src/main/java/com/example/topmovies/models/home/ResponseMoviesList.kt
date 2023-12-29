package com.example.topmovies.models.home


import com.google.gson.annotations.SerializedName

data class ResponseMoviesList(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("metadata")
    var metadata: Metadata?
) {
    data class Data(
        @SerializedName("country")
        var country: String?, // USA
        @SerializedName("genres")
        var genres: List<String?>?,
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("images")
        var images: List<String?>?,
        @SerializedName("imdb_rating")
        var imdbRating: String?, // 9.3
        @SerializedName("poster")
        var poster: String?, // https://www.moviesapi.ir/images/tt0111161_poster.jpg
        @SerializedName("title")
        var title: String?, // The Shawshank Redemption
        @SerializedName("year")
        var year: String? // 1994
    )

    data class Metadata(
        @SerializedName("current_page")
        var currentPage: String?, // 1
        @SerializedName("page_count")
        var pageCount: Int?, // 6
        @SerializedName("per_page")
        var perPage: Int?, // 10
        @SerializedName("total_count")
        var totalCount: Int? // 59
    )
}