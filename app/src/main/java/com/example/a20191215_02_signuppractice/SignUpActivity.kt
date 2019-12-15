package com.example.a20191215_02_signuppractice

import android.os.Bundle

//SignUpActivity로 변경
class SignUpActivity : BaseActivity() {

    var lastBackTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }


    override fun setEvents() {

        lastBackTime = 10L

    }

    override fun setValue() {

        lastBackTime = 20L

    }

}
