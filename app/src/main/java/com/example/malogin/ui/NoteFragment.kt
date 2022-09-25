package com.example.malogin.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.malogin.R
import com.example.malogin.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private val spLogin = "spLogin"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(spLogin, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val username = "Welcome, " + sharedPreferences.getString("username_key", null) + "!"
        binding.tvUser.text = username

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_loginFragment)
            editor.clear()
            editor.apply()
        }
    }
}