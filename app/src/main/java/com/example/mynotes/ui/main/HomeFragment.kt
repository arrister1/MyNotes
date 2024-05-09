package com.example.mynotes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentHomeBinding
import com.example.mynotes.model.Note


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeMainViewModel: MainViewModel
    private lateinit var noteAdapter: NotesAdapter

    private lateinit var currentNote: Note

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMainViewModel = (activity as MainActivity)
            . mainViewModel

        binding.btnAdd.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        }
        setHomeRecycleView()

    }

    private fun updateNoteList(note: List<Note>?) {
        if (note != null){
            if(note.isNotEmpty()) {
                binding.emptyNotesImage.visibility = View.GONE
            } else{
                 binding.emptyNotesImage.visibility = View.VISIBLE
                binding.homeRv.visibility = View.GONE
            }
        }
    }

    private fun setHomeRecycleView(){
        noteAdapter = NotesAdapter(requireContext(), homeMainViewModel)
        binding.homeRv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            homeMainViewModel.getAllNote().observe(viewLifecycleOwner){ noteList ->
                noteAdapter.setNoteList(noteList)
                updateNoteList(noteList)
            }
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                homeMainViewModel.delete(currentNote)
                Toast.makeText(context, "Not has been deleted", Toast.LENGTH_SHORT)
               // view?.findNavController()?.popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}