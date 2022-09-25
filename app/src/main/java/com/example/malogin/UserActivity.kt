package com.example.malogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.malogin.helper.Constant
import com.example.malogin.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        sharedPref = PreferenceHelper(this)

        tv_user.text = sharedPref.getString(Constant.PREF_USERNAME)

        btn_logout.setOnClickListener {
            sharedPref.clear()
            moveIntent()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}