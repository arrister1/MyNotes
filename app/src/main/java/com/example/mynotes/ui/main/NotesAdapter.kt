package com.example.mynotes.ui.main

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ItemNotesBinding

import com.example.mynotes.helper.NoteDiffCallback
import com.example.mynotes.model.Note

class NotesAdapter(private val context: Context, private val noteViewModel: MainViewModel) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val noteList = ArrayList<Note>()

    //private lateinit var noteViewModel: MainViewModel
    private var note: Note? = null
    private lateinit var noteView: View

    fun setNoteList(noteList: List<Note>) {
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

    }

    inner class NoteViewHolder(val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                noteTitle.text = note.title
                noteContent.text = note.description
                btnEdit.setOnClickListener {
                    val direction =
                        HomeFragmentDirections.actionHomeFragmentToNoteEditFragment(note)
                    it.findNavController().navigate(direction)
                }
                btnDelete.setOnClickListener {
                    showDeleteDialog(ALERT_DIALOG_DELETE, note)
                }
            }
        }


    }

    private fun showDeleteDialog(type: Int, note: Note?) {
        if (note != null) {
            val isDialogDelete = type == ALERT_DIALOG_DELETE
            val dialogTitle: String
            val dialogMessage: String
            if (isDialogDelete) {
                dialogTitle = "Delete"
                dialogMessage = "Do you want to delete this note?"
            } else {
                dialogMessage = "Do you want to delete this note?"
                dialogTitle = "Delete"
            }
            var alertDialogBuilder = AlertDialog.Builder(context)
            with(alertDialogBuilder) {
                setTitle(dialogTitle)
                setMessage(dialogMessage)
                setCancelable(false)
                setPositiveButton("Yes") { _, _ ->
                    if (isDialogDelete) {
                        noteViewModel.delete(note)
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                        //showToast(getString(R.string.delete))
                    }
                }
                setNegativeButton("No") { dialog, _ -> dialog.cancel() }
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } else {
            Toast.makeText(context, "Note is null", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}
