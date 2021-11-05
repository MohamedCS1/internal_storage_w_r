package com.example.internal_storage_r_w

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {

    var bu_insert:Button? = null
    var bu_get:Button? = null
    var bu_clearall:Button? = null
    var tv_alltext:TextView? = null
    var et_email:EditText? = null
    var et_password:EditText? = null
    val FILENAME = "Anyname"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bu_insert = findViewById(R.id.bu_insert)
        bu_get = findViewById(R.id.bu_get)
        bu_clearall = findViewById(R.id.bu_clearall)

        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        tv_alltext = findViewById(R.id.tv_file_text)


        var file:FileOutputStream? = null
        var pw:PrintWriter? = null
        bu_insert!!.setOnClickListener {

            file = openFileOutput(FILENAME , MODE_PRIVATE)
            pw = PrintWriter(file!!)
            pw!!.println("${et_email!!.text} ,${et_password!!.text}")
            pw!!.close()
            file!!.close()
        }

        bu_get!!.setOnClickListener {
            val fileInputStream = openFileInput(FILENAME)
            val streamreader = InputStreamReader(fileInputStream)
            val bfr = BufferedReader(streamreader)
            var alltext = ""
            var line = bfr.readLine()
            while (line != null)
            {
                Log.d("bfr" ,bfr.readText())
                alltext += line
                tv_alltext!!.text = alltext
                line =  bfr.readLine()

            }
            bfr.close()
            streamreader.close()
            fileInputStream.close()

        }
        bu_clearall!!.setOnClickListener {
            file = openFileOutput(FILENAME , MODE_PRIVATE)
            pw = PrintWriter(file!!)
            pw!!.println("")
            pw!!.close()
            file!!.close()
        }

    }
}

