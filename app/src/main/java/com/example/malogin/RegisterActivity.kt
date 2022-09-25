package com.example.malogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.malogin.databinding.ActivityMainBinding
import com.example.malogin.databinding.ActivityRegisterBinding
import com.example.malogin.helper.PreferenceHelper

class RegisterActivity : AppCompatActivity() {
    lateinit var sharedPref : PreferenceHelper
    private lateinit var binding : ActivityRegisterBinding

    companion object {
        const val FORM_MODE_INSERT = 0
        const val FORM_MODE_EDIT = 1
        const val INTENT_FORM_MODE = "INTENT_FORM_MODE"
        const val INTENT_NOTE_DATA = "INTENT_NOTE_DATA"
        private const val DEFAULT_CARD_COLOR = "#d3bdff"

        @JvmStatic
        fun startActivity(context: Context?) {
            val intent = Intent(context, RegisterActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}