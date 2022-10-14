package com.example.malogin.ui.editnote

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.malogin.R
import com.example.malogin.database.entity.NoteEntity
import com.example.malogin.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private val spLogin = "spLogin"

    private lateinit var editViewModel: EditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(spLogin, Context.MODE_PRIVATE)
//        val application = requireNotNull(this.activity).application
//        val dataSource = AppDatabase.getInstance(application).noteDatabaseDao()
//        val viewModelFactory = CreateViewModelFactory(dataSource, application)
//        createViewModel = ViewModelProvider(this, viewModelFactory)[CreateViewModel::class.java]
        val noteId = sharedPreferences.getInt("note_id", 0)
//        binding.tvCreateNew.text = noteId.toString()
//        binding.tvEditNote.text = noteId.toString()
        binding.btnSave.setOnClickListener{ saveNote() }
    }

    private fun saveNote() {
        val title = binding.etTitle.text.toString()
        val note = binding.etNote.text.toString()

//        createViewModel.insertNote(NoteEntity(title = title, note = note))
//        sharedPreferences.edit {
//            this.putInt("note_count", noteCount)
//        }
        editViewModel.updateNote(NoteEntity(title = title, note = note))
        Toast.makeText(requireContext(), "Berhasil menyimpan", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_createFragment_to_noteFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}