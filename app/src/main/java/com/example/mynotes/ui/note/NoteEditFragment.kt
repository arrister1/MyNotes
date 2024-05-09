package com.example.mynotes.ui.note

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNoteBinding
import com.example.mynotes.databinding.FragmentNoteEditBinding
import com.example.mynotes.model.Note
import com.example.mynotes.ui.main.MainActivity
import com.example.mynotes.ui.main.MainViewModel


class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {
    private var _binding: FragmentNoteEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: MainViewModel
    private lateinit var currentNote: Note

    private val args: NoteEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteEditBinding.inflate(inflater, container, false)
        return    binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).mainViewModel
        currentNote = args.note!!

        binding.notesInputTitle.setText(currentNote.title)
        binding.notesInputDesc.setText(currentNote.description)

        binding.btnEdit.setOnClickListener {
            val noteTitle = binding.notesInputTitle.text.toString().trim()
            val noteDesc = binding.notesInputDesc.text.toString().trim()

            if(noteTitle.isNotEmpty()) {
                val note = Note(currentNote.id, noteTitle, noteDesc)
                noteViewModel.update(note)
                view.findNavController().popBackStack(R.id.homeFragment, false)

            } else {
                Toast.makeText(context, "Title can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteNote(){
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                noteViewModel.delete(currentNote)
                Toast.makeText(context, "Not has been deleted", Toast.LENGTH_SHORT)
                view?.findNavController()?.popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}