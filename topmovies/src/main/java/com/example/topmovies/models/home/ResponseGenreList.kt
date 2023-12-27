package com.example.topmovies.models.home


import com.google.gson.annotations.SerializedName

class ResponseGenreList : ArrayList<ResponseGenreList.ResponseGenreListItem>(){
    data class ResponseGenreListItem(
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("name")
        var name: String? // Crime
    )
}