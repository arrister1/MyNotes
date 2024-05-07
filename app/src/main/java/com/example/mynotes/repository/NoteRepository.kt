package com.example.mynotes.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotes.db.NoteDao
import com.example.mynotes.db.NoteRoomDB
import com.example.mynotes.model.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val notedb = NoteRoomDB.getDatabase(application)
        mNoteDao = notedb.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNoteDao.getListNotes()

    fun insert(note: Note) {
        executorService.execute { mNoteDao.insert(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNoteDao.update(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNoteDao.delete(note) }
    }
}