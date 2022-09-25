package com.example.malogin.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.malogin.R
import com.example.malogin.databinding.FragmentLoginBinding
import com.example.malogin.database.AppDatabase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private val spLogin = "spLogin"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(spLogin, MODE_PRIVATE)

        val application = requireNotNull(this.activity).application
        val data = AppDatabase.getInstance(application).accountDatabaseDao()
        val viewModelFactory = LoginViewModelFactory(data, application)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.btnRegister.setOnClickListener{findNavController().navigate(R.id.action_loginFragment_to_registerFragment)}
        binding.btnLogin.setOnClickListener{login()}

        autoConnect()
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        loginViewModel.getAccountByEmail(email).observe(viewLifecycleOwner){
            if (it.email == email && it.password == password){
                findNavController().navigate(R.id.action_loginFragment_to_noteFragment)
                sharedPreferences.edit {
                    this.putString("username_key", it.username)
                }
            }
            else{
                Toast.makeText(requireContext(), "Akun tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun autoConnect(){
        val connected = sharedPreferences.getBoolean(PreferenceKey.PREF_USER_LOGIN_KEY.first,
            PreferenceKey.PREF_USER_LOGIN_KEY.second)

        if (connected){
            findNavController().navigate(R.id.action_loginFragment_to_noteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

object PreferenceKey{
    val PREF_USER_LOGIN_KEY = Pair("PREF_LOGIN_APP_KEY", false)
}