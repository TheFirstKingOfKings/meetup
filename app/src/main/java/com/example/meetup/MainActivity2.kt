package com.example.meetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv_number_label.text = intent.getIntExtra("NUMBER", -1).toString()

        button?.setOnClickListener{
            tv_number_label?.text = Net.getMovieFromJSON()
        }

    }



}