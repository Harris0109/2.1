package com.example.moneymate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        val etCategoryName = findViewById<EditText>(R.id.etCategoryName)
        val btnSaveCategory = findViewById<Button>(R.id.btnSaveCategory)

        btnSaveCategory.setOnClickListener {
            val categoryName = etCategoryName.text.toString().trim()

            if (categoryName.isNotEmpty()) {
                // For now, just show success message
                Toast.makeText(this, "Category '$categoryName' Saved!", Toast.LENGTH_SHORT).show()
                etCategoryName.text.clear()
            } else {
                Toast.makeText(this, "Please enter a category name.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
