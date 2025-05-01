package com.example.modul6pamrecyclerview.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.modul6pamrecyclerview.R

class AddPetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        val etName = findViewById<EditText>(R.id.etName)
        val etType = findViewById<EditText>(R.id.etType)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etOwner = findViewById<EditText>(R.id.etOwner)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val type = etType.text.toString()
            val age = etAge.text.toString().toIntOrNull() ?: 0
            val owner = etOwner.text.toString()

            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("type", type)
                putExtra("age", age)
                putExtra("owner", owner)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
