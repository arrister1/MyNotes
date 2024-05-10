package com.example.mynotes.ui.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNoteBinding
import com.example.mynotes.databinding.FragmentRegisterBinding
import com.example.mynotes.helper.Constant
import com.example.mynotes.helper.UserSharedPreference


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    lateinit var sharedPref: UserSharedPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = UserSharedPreference(requireActivity())


        binding.btnRegis.setOnClickListener {

            if (binding.etRegisUname.text!!.isNotEmpty()) {
                sharedPref.put(Constant.PREF_USERNAME, binding.etRegisUname.text.toString())
                sharedPref.put(Constant.PREF_EMAIL, binding.etRegisEmail.text.toString())
                sharedPref.put(Constant.PREF_PASS, binding.etRegisPass1.text.toString())
            }

//
            Toast.makeText(context, "Ragistration success", Toast.LENGTH_SHORT).show()

            view.findNavController().popBackStack(R.id.loginFragment, false)
        }

    }
}