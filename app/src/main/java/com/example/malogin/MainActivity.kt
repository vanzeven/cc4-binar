package com.example.malogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.malogin.databinding.ActivityMainBinding
import com.example.malogin.helper.Constant
import com.example.malogin.helper.PreferenceHelper

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref : PreferenceHelper
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = PreferenceHelper(this)

        binding.btnLogin.setOnClickListener {
            if(binding.etUsername.text.isNotEmpty()) {
                sharedPref.put(Constant.PREF_USERNAME, binding.etUsername.text.toString())
                sharedPref.put(Constant.PREF_PASSWORD, binding.etPassword.text.toString())
                sharedPref.put(Constant.PREF_IS_LOGIN, true)
                moveIntent()
            }
        }

        var passIsVisible = 0
        binding.btnShowPassword?.setOnClickListener {
            if(passIsVisible == 0) {
                binding.btnShowPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passIsVisible = 1
            } else {
                binding.btnShowPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                passIsVisible = 0
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getBoolean(Constant.PREF_IS_LOGIN)){
            moveIntent()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }
}