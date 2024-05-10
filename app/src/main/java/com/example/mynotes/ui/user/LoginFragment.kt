package com.example.mynotes.ui.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentLoginBinding
import com.example.mynotes.helper.Constant
import com.example.mynotes.helper.UserSharedPreference


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var sharedPref: UserSharedPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = UserSharedPreference(requireActivity())

        binding.btnLogin.setOnClickListener {
            if (binding.etLoginEmail.text!!.isNotEmpty()) {
                sharedPref.put(Constant.PREF_EMAIL, binding.etLoginEmail.text.toString())
                sharedPref.put(Constant.PREF_PASS, binding.etLoginPass.text.toString())
                sharedPref.put(Constant.PREF_IS_LOGIN, true)


                Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            }


        }

        binding.tvLoginReg.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getBoolean(Constant.PREF_IS_LOGIN)) {
            requireView().findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }
    }
}