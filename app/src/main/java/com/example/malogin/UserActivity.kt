package com.example.malogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.malogin.databinding.ActivityMainBinding
import com.example.malogin.databinding.ActivityUserBinding
import com.example.malogin.helper.Constant
import com.example.malogin.helper.PreferenceHelper

class UserActivity : AppCompatActivity() {
    lateinit var sharedPref: PreferenceHelper
    private lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = PreferenceHelper(this)

        binding.tvUser.text = sharedPref.getString(Constant.PREF_USERNAME)

        binding.btnLogout.setOnClickListener {
            sharedPref.clear()
            moveIntent()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}