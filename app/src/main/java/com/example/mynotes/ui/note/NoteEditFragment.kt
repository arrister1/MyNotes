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

        binding.btnBack.setOnClickListener{
            view.findNavController().popBackStack()
        }

        binding.btnEdit.setOnClickListener {
            val noteTitle = binding.notesInputTitle.text.toString().trim()
            val noteDesc = binding.notesInputDesc.text.toString().trim()

            if(noteTitle.isNotEmpty()) {
                val note = Note(currentNote.id, noteTitle, noteDesc)
                noteViewModel.update(note)
                view.findNavController().popBackStack(R.id.homeFragment, false)
                Toast.makeText(context, "Note has been edited", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Title can't be empty", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}