package com.example.koin.room.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.koin.NOTE_DATABASE
import com.example.koin.room.repository.RoomRepository
import com.example.koin.room.viewmodel.RoomViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*fun provideDatabase(context: Context) = Room.databaseBuilder(context , NoteDatabase::class.java , NOTE_DATABASE)
    .allowMainThreadQueries()
    .fallbackToDestructiveMigration()
    .build()

fun provideDao(db : NoteDatabase) = db.noteDao()*/
/*
val dataBaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
    factory { RoomRepository(get()) }
    viewModel { RoomViewModel(get()) }
}*/
