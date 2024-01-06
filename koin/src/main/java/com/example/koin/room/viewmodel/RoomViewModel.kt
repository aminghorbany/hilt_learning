package com.example.koin.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin.room.db.MyNoteModel
import com.example.koin.room.repository.RoomRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class RoomViewModel(private val repo : RoomRepository):ViewModel() {

    val notesList = repo.getAllNotes()

    fun saveNote(notesModel: MyNoteModel) = viewModelScope.launch {
        repo.saveNote(notesModel)
    }
}

val roomViewModelModule = module {
    factory { RoomViewModel(get()) }
}