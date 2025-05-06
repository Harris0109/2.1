package com.example.moneymate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var etCategoryName: EditText
    private lateinit var etCategoryDescription: EditText
    private lateinit var btnSaveCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        // Connect XML elements to Kotlin variables
        etCategoryName = findViewById(R.id.etCategoryName)
        etCategoryDescription = findViewById(R.id.etCategoryDescription)
        btnSaveCategory = findViewById(R.id.btnSaveCategory)

        btnSaveCategory.setOnClickListener {
            val name = etCategoryName.text.toString().trim()
            val description = etCategoryDescription.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty()) {
                Toast.makeText(this, "Category '$name' Saved!", Toast.LENGTH_SHORT).show()
                etCategoryName.text.clear()
                etCategoryDescription.text.clear()
            } else {
                Toast.makeText(this, "Please enter both name and description.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
