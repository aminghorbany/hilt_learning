package com.example.topmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.topmovies.utils.Constants

@Entity(tableName = Constants.MOVIES_TABLE)
data class MoviesEntity(
    @PrimaryKey // we don't need autoGenerate because each movie have ID in the server //do not set autoGenerate
    var id : Int = 0 ,
    var poster : String = "" ,
    var title : String = "" ,
    var rate : String = "" ,
    var country : String = "" ,
    var year : String = "" ,

)
