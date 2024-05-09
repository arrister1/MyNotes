package com.example.mynotes.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.db.NoteRoomDB
import com.example.mynotes.helper.ViewModelFactory
import com.example.mynotes.repository.NoteRepository

class MainActivity : AppCompatActivity() {
    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding


    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewModel()

    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(application)
        val viewModelProviderFactory = ViewModelFactory(application)
        mainViewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]

    }
}