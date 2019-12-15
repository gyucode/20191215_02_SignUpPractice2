package com.example.a20191215_02_signuppractice

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this


    abstract fun setEvents()
    abstract fun setValue()
}