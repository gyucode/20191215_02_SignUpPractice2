package com.example.a20191215_02_signuppractice

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

//SignUpActivity로 변경
class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setEvents()
        setValue()

    }


    override fun setEvents() {

        birthDayTxt.setOnClickListener {
//            Toast.makeText(mContext,"생일 지정 텍스트뷰 클릭",Toast.LENGTH_SHORT).show()
            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val selectedDateStr = "${year} / ${month}/ ${dayOfMonth}"
                birthDayTxt.text = selectedDateStr

//                자바에서는 월을 0 ~ 11 월로 사용. 생각하는 것보다 1작은 숫자를 월로 넣어줘야함

            },2018,Calendar.DECEMBER,15)

            datePickerDialog.show()
        }

        pwEdt.addTextChangedListener {
            Log.d("입력된값",it.toString())
            val inputStr = it.toString()
            if(inputStr.length == 0 ){
                pwStateTxt.text = "비밀번호가 입력되지 않았습니다."
                pwStateTxt.setTextColor(Color.RED)
            }
            else if (inputStr.length < 8){
                pwStateTxt.text = "입력된 비번이 너무 짧습니다"
                pwStateTxt.setTextColor(Color.parseColor("#FDA0EF"))
            }
            else{
                pwStateTxt.setTextColor(Color.GREEN)
                pwStateTxt.text = "사용해도 좋은 비밀번호입니다."
            }
        }
//        pwEdt.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                Log.d("입력된 값",s.toString())
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//            }
//
//        })


    }

    override fun setValue() {


    }

}
