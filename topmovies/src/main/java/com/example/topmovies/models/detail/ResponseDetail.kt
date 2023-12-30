package com.example.topmovies.models.detail


import com.google.gson.annotations.SerializedName

data class ResponseDetail(
    @SerializedName("actors")
    var actors: String?, // Edward Norton, Brad Pitt, Meat Loaf, Zach Grenier
    @SerializedName("awards")
    var awards: String?, // Nominated for 1 Oscar. Another 10 wins & 31 nominations.
    @SerializedName("country")
    var country: String?, // USA, Germany
    @SerializedName("director")
    var director: String?, // David Fincher
    @SerializedName("genres")
    var genres: List<String?>?,
    @SerializedName("id")
    var id: Int?, // 10
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("imdb_id")
    var imdbId: String?, // tt0137523
    @SerializedName("imdb_rating")
    var imdbRating: String?, // 8.8
    @SerializedName("imdb_votes")
    var imdbVotes: String?, // 1,384,393
    @SerializedName("metascore")
    var metascore: String?, // 66
    @SerializedName("plot")
    var plot: String?, // An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more.
    @SerializedName("poster")
    var poster: String?, // https://www.moviesapi.ir/images/tt0137523_poster.jpg
    @SerializedName("rated")
    var rated: String?, // R
    @SerializedName("released")
    var released: String?, // 15 Oct 1999
    @SerializedName("runtime")
    var runtime: String?, // 139 min
    @SerializedName("title")
    var title: String?, // Fight Club
    @SerializedName("type")
    var type: String?, // movie
    @SerializedName("writer")
    var writer: String?, // Chuck Palahniuk (novel), Jim Uhls (screenplay)
    @SerializedName("year")
    var year: String? // 1999
)