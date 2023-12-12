package com.example.hilt2.di

import android.content.Context
import androidx.room.Room
import com.example.hilt2.NOTE_DATABASE
import com.example.hilt2.room.db.NotesDataBase
import com.example.hilt2.room.db.NotesModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {


    @Provides
    @Singleton
    fun provideEntity() : NotesModel = NotesModel()

    @Provides
    @Singleton
    fun provideDao(db : NotesDataBase) = db.notesDao()

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(context , NotesDataBase::class.java , NOTE_DATABASE )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

}