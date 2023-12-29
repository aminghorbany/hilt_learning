package com.example.topmovies.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.topmovies.db.MoviesDao
import com.example.topmovies.db.MoviesDataBase
import com.example.topmovies.db.MoviesEntity
import com.example.topmovies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatBase(@ApplicationContext context: Context) : MoviesDataBase =
        Room.databaseBuilder(context , MoviesDataBase::class.java , Constants.MOVIES_DATABASE )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDao(db : MoviesDataBase) = db.moviesDao()

    @Provides
    @Singleton
    fun provideEntity() : MoviesEntity = MoviesEntity()

}