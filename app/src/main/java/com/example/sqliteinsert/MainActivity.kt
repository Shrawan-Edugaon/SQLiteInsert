package com.example.sqliteinsert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var name:EditText
    lateinit var lastname:EditText
    lateinit var height:EditText
    lateinit var weight:EditText
    lateinit var btn:Button
    lateinit var databaseHelper:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.EdtName)
        lastname = findViewById(R.id.EdtLastName)
        height = findViewById(R.id.EdtHeight)
        weight = findViewById(R.id.EdtWeight)
        btn = findViewById(R.id.InsertButton)
        databaseHelper = DatabaseHelper(this)

        btn.setOnClickListener {
            insertFunction()
        }
    }


    private fun insertFunction() {
        val strName = name.text.toString()
        val strLastName = lastname.text.toString()
        val strHeight = height.text.toString()
        val strWeight = weight.text.toString()

        val result:Boolean = databaseHelper.insertData(strName,strLastName,strHeight,strWeight)

        when{
            result ->Toast.makeText(applicationContext,"Data Inserted Successfully...",Toast.LENGTH_LONG).show()
            else ->Toast.makeText(applicationContext,"Failed to insert data...",Toast.LENGTH_LONG).show()
        }
    }
}