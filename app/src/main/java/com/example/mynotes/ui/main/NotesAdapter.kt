package com.example.mynotes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ItemNotesBinding
import androidx.navigation.fragment.findNavController
import com.example.mynotes.R

import com.example.mynotes.helper.NoteDiffCallback
import com.example.mynotes.model.Note

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val noteList = ArrayList<Note>()
//    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
//        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
//            return oldItem.id == newItem.id &&
//                    oldItem.description == newItem.description &&
//                    oldItem.title == newItem.title
//        }
//
//        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
//            return oldItem == newItem
//        }
//    }

    //private val differ = AsyncListDiffer(this, differCallback)

    fun setNoteList(noteList: List<Note>){
        val diffCallback = NoteDiffCallback(this.noteList, noteList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.noteList.clear()
        this.noteList.addAll(noteList)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
//        holder.binding.btnEdit.setOnClickListener{
//            val direction = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
//
//        }
    }

    inner class NoteViewHolder( val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note) {
            with(binding) {
                noteTitle.text = note.title
                noteContent.text=note.description
                btnEdit.setOnClickListener{
                    val direction = HomeFragmentDirections.actionHomeFragmentToNoteFragment()
                    it.findNavController().navigate(direction)
                }
            }
        }



    }

}