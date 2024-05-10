package com.example.mynotes.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNoteBinding
import com.example.mynotes.model.Note
import com.example.mynotes.ui.main.MainActivity
import com.example.mynotes.ui.main.MainViewModel


class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: MainViewModel
    private  lateinit var noteView: View

   private  var currentNote: Note? = null
    private var isEdit = false


    companion object {
        const val EXTRA_NOTE = "extra_note"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).mainViewModel
        noteView = view

        if(currentNote != null) {
            isEdit = true
        } else {
            currentNote = Note()
        }

        binding?.btnCreate?.setOnClickListener {
            val title = binding?.notesInputTitle?.text.toString().trim()
            val description = binding?.notesInputDesc?.text.toString().trim()
            when {
                title
                    .isEmpty() -> {
                    binding?.notesInputTitle?.error = "Title can't be empty"
                }
                description.isEmpty() -> {
                    binding?.notesInputTitle?.error = "Description can't be empty"
                }
                else -> {
                    currentNote.let { note ->
                        note?.title = title
                        note?.description = description
                    }
                    if(isEdit) {
                        noteViewModel.update(currentNote as Note)
                        showToast("Note has changed")
                    } else {
                        noteViewModel.insert(currentNote as Note)
                        showToast("Note has added")
                        view.findNavController().popBackStack(R.id.homeFragment, false)
                    }
               }
            }
        }
        
    }



    private fun showToast(message: String){
            Toast.makeText(noteView.context, message, Toast.LENGTH_SHORT).show()
            //view.findNavController().popBackStack(R.id.homeFragment, false)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}