package com.example.malogin.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malogin.R
import com.example.malogin.database.AppDatabase
import com.example.malogin.database.entity.NoteEntity
import com.example.malogin.databinding.FragmentNoteBinding
import com.example.malogin.ui.NoteAdapter
import com.example.malogin.ui.NoteItemClickListener
import com.example.malogin.ui.createnote.CreateViewModelFactory

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

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
        val username = "Welcome, " + sharedPreferences.getString("username_key", null) + "!"
        binding.tvUser.text = username
//        if(sharedPreferences.getString("note_count", null) == ""){
//            sharedPreferences.edit {
//                this.putInt("note_count", 0)
//            }
//            binding.isEmpty = true
//        }

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).noteDatabaseDao()
        val viewModelFactory = CreateViewModelFactory(dataSource, application)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        val recyclerView = binding.rvNote

        adapter = NoteAdapter(object: NoteItemClickListener {
            override fun onDeleteMenuClicked(item: NoteEntity) {
//                adapter.deleteItem(item)
//                noteViewModel.deleteNote(item)
//                Toast.makeText(requireContext(), "Note berhasil dihapus", Toast.LENGTH_SHORT).show()
                showDeleteDialog(item)
            }

            override fun onEditMenuClicked(item: NoteEntity) {
//                Toast.makeText(requireContext(), "To be implemented", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_noteFragment_to_editFragment)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        noteViewModel.getNote().observe(viewLifecycleOwner){
            adapter.setItems(it)
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_loginFragment)
            sharedPreferences.edit {
                this.remove("username_key")
            }
        }

        binding.btnAdd.setOnClickListener {findNavController().navigate(R.id.action_noteFragment_to_createFragment)}
//        binding.btnAdd.setOnClickListener { showDialog() }
    }


    private fun showDeleteDialog(item: NoteEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Data")
            .setPositiveButton("HAPUS") { _, _ ->
                adapter.deleteItem(item)
                noteViewModel.deleteNote(item)
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
        var noteCount = sharedPreferences.getInt("note_count", 0)
        noteCount -= 1
        sharedPreferences.edit {
            this.putInt("note_count", noteCount)
        }
        binding.isEmpty = noteCount == 0
    }
}