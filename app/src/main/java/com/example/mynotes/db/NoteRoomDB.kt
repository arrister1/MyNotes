package com.example.mynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDB: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDB? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDB {
            if (INSTANCE == null) {
                synchronized(NoteRoomDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteRoomDB::class.java, "note_databases")
                        .build()
                }
            }
            return INSTANCE as NoteRoomDB
        }
    }
}