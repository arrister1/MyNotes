package com.example.mynotes.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.mynotes.model.Note


//buat ngecek apa ada perbuahan list note. Akan dipanggil di adapter
class NoteDiffCallback(private val oldNoteList: List<Note>, private val newNoteList: List<Note>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNoteList.size

    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNoteItem = oldNoteList[oldItemPosition]
        val newNoteItem = newNoteList[newItemPosition]
        return oldNoteItem.title == newNoteItem.title && oldNoteItem.description == newNoteItem.description
    }

}