package com.example.koin.room.repository

import com.example.koin.room.db.NoteDao
import com.example.koin.room.db.MyNoteModel
import org.koin.dsl.module

class RoomRepository(private val dao : NoteDao) {
    suspend fun saveNote(noteModel: MyNoteModel) = dao.saveNote(noteModel)
    fun getAllNotes() = dao.getAllNote()
}

val roomRepositoryModule = module {
    factory { RoomRepository(get()) }
}