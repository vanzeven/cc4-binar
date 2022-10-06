package com.example.malogin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.malogin.R
import com.example.malogin.database.AppDatabase
import com.example.malogin.database.entity.NoteEntity
import com.example.malogin.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var createViewModel: CreateViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).noteDatabaseDao()
        val viewModelFactory = CreateViewModelFactory(dataSource, application)
        createViewModel = ViewModelProvider(this, viewModelFactory)[CreateViewModel::class.java]

        binding.btnSave.setOnClickListener{ saveNote() }
    }

    private fun saveNote() {
        val title = binding.etTitle.text.toString()
        val note = binding.etNote.text.toString()

        createViewModel.insertNote(NoteEntity(title = title, note = note))
        Toast.makeText(requireContext(), "Berhasil menyimpan", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_createFragment_to_noteFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}