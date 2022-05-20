package com.example.myapp2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.logging.Logger.global



class MainActivity : AppCompatActivity() {
    var count = 0
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Mylog", "onCreate")

        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        count = pref?.getInt("count", 0)!!

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = count.toString()
    }


    fun onClickButton(view: View) {
        val textView = findViewById<TextView>(R.id.textView)
        count++
        textView.text = count.toString()
    }

    fun saveData(res: Int) {
        val editor = pref?.edit()
        editor?.putInt("count", res)
        editor?.apply()
    }

    fun deleteAll(){
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("Mylog", "onDestroy")
        saveData(count)
    }

    fun onClickDel(view: View) {
        count = 0
        deleteAll()
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = count.toString()
    }
}