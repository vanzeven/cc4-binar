package com.example.malogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.malogin.helper.Constant
import com.example.malogin.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref : PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = PreferenceHelper(this)

        btn_login.setOnClickListener {
            if(et_username.text.isNotEmpty()) {
                sharedPref.put(Constant.PREF_USERNAME, et_username.text.toString())
                sharedPref.put(Constant.PREF_PASSWORD, et_password.text.toString())
                sharedPref.put(Constant.PREF_IS_LOGIN, true)
                moveIntent()
            }
        }

        var passIsVisible = 0
        btnShowPassword?.setOnClickListener {
            if(passIsVisible == 0) {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passIsVisible = 1
            } else {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
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