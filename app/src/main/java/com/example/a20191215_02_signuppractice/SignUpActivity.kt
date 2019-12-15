package com.example.a20191215_02_signuppractice

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.a20191215_02_signuppractice.adapters.AlcoholAdapter
import com.example.a20191215_02_signuppractice.datas.Alcohol
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//SignUpActivity로 변경
class SignUpActivity : BaseActivity() {

    val alcoholList = ArrayList<Alcohol>()
    var alcoholAdapter:AlcoholAdapter? = null
    
    var selectedBirthDay:Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setEvents()
        setValue()

    }


    override fun setEvents() {

        jobSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0) {
                    Toast.makeText(mContext, jobSpinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }

        birthTimeTxt.setOnClickListener {
            val timePickerDialog = TimePickerDialog(mContext, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

//                시간을 캘린더에 저장
                selectedBirthDay?.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedBirthDay?.set(Calendar.MINUTE, minute )

//                캘린더에 저장된 값을 SimpleDateFormat을 이용해 화면 출력
                val sdf = SimpleDateFormat("a h:mm")
                birthTimeTxt.text = sdf.format(selectedBirthDay?.time)

            }, 20, 5, true)
            timePickerDialog.show()
        }

        birthDayTxt.setOnClickListener {
//            Toast.makeText(mContext,"생일 지정 텍스트뷰 클릭",Toast.LENGTH_SHORT).show()
            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val selectedDateStr = "${year} / ${month}/ ${dayOfMonth}"
                birthDayTxt.text = selectedDateStr

                selectedBirthDay?.let{
                    Log.d("생년월일선택","이미 선택된 값을 수정 - 다시선택")

                }.let {
                    Log.d("생년월일선택","선택된 값이 새로 생김 - 처음선택")
                    selectedBirthDay = Calendar.getInstance()
//                    Q. selectedBirthDay에 담긴 날짜는 몇월 몇일? A. 인스턴스 생성날짜.(12/15)
//                    이 담긴 날짜를 선택한 년/월/일로 대입.
                }

                selectedBirthDay?.set(Calendar.YEAR, year)
                selectedBirthDay?.set(Calendar.MONTH, month)
                selectedBirthDay?.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                selectedBirthDay?.set(year, month, dayOfMonth)

//                저장된 생년월일을 simpleDateFormat을 이용해 출력

                val sdf = SimpleDateFormat("yyyy년 M월 d일 (E)")
                birthDayTxt.text = sdf.format(selectedBirthDay?.time)

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
        addAlcohols()

        alcoholAdapter = AlcoholAdapter(mContext, R.layout.alcohol_spinner_list_item, alcoholList)
        alcoholSpinner.adapter = alcoholAdapter


    }

    fun addAlcohols(){
        alcoholList.add(Alcohol("소주","참이슬"))
        alcoholList.add(Alcohol("소주","처음처럼"))
        alcoholList.add(Alcohol("소주","좋은데이"))
        alcoholList.add(Alcohol("소주","한라산"))
        alcoholList.add(Alcohol("국내맥주","카스"))
        alcoholList.add(Alcohol("국내맥주","테라"))
        alcoholList.add(Alcohol("국내맥주","맥스"))
        alcoholList.add(Alcohol("해외맥주","하이네캔"))
        alcoholList.add(Alcohol("해외맥주","호가든"))
        alcoholList.add(Alcohol("해외맥주","칭따오"))
    }

}
