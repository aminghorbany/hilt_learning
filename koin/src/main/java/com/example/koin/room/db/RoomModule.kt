package com.example.koin.room.db

import android.content.Context
import androidx.room.Room
import com.example.koin.NOTE_DATABASE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/*fun provideDatabase(context: Context) =
    Room.databaseBuilder(context , NoteDatabase::class.java , NOTE_DATABASE)
    .allowMainThreadQueries()
    .fallbackToDestructiveMigration()
    .build()

fun provideDao(db : NoteDatabase) = db.noteDao()*/

/*
val databaseModule =  module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }

}*/
