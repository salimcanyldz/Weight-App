package com.ssdarklord.weightapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val POUND = 2.2045
    val KILO = 0.45359237
    val MARS = 0.38
    val JUPITER = 2.34
    val VENUS = 0.91

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult.text = savedInstanceState?.getString("result")
        cb1.setOnClickListener(this)
        cb2.setOnClickListener(this)
        cb3.setOnClickListener(this)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", tvResult.text.toString())

    }

    fun kiloToPound(weight: Double): Double{

        return weight * POUND
    }

    fun poundToKilo(weight: Double): Double{

        return weight * KILO
    }

    override fun onClick(v: View?) {

        v as CheckBox

        var isChecked: Boolean = v.isChecked


        if (!TextUtils.isEmpty(userWeight.text.toString())){
            var userInput = kiloToPound(userWeight.text.toString().toDouble())
            when (v.id){

                R.id.cb1 -> if (isChecked){
                    cb2.isChecked = false
                    cb3.isChecked = false
                    calcWeight(userInput, v)
                }
                R.id.cb2 -> if(isChecked){
                    cb1.isChecked = false
                    cb3.isChecked = false
                    calcWeight(userInput, v)
                }
                R.id.cb3 -> if(isChecked){
                    cb1.isChecked = false
                    cb2.isChecked = false
                    calcWeight(userInput, v)
                }
            }
        }


    }

    fun calcWeight(pound: Double, checkBox: CheckBox){

        var result: Double = 0.0

        when (checkBox.id){

            R.id.cb1 -> result = pound * MARS
            R.id.cb2 -> result = pound * JUPITER
            R.id.cb3 -> result = pound * VENUS
            else -> result = 0.0
        }

        var resKg = poundToKilo(result)
        tvResult.text = resKg.trimNumber(2).toString()
    }

    fun Double.trimNumber(num: Int) = java.lang.String.format("%.${num}f", this)

}