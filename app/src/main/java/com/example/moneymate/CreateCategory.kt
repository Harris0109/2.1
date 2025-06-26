package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateCategoryActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var etCategoryName: EditText
    private lateinit var etCategoryDescription: EditText
    private lateinit var btnSaveCategory: Button
    private lateinit var btnBack: Button
    private lateinit var tvLogout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        // Initialize UI components
        etCategoryName = findViewById(R.id.etCategoryName)
        etCategoryDescription = findViewById(R.id.etCategoryDescription)
        btnSaveCategory = findViewById(R.id.btnSaveCategory)
        btnBack = findViewById(R.id.backArrow)
        tvLogout = findViewById(R.id.logout)

        // Save button logic
        btnSaveCategory.setOnClickListener {
            val name = etCategoryName.text.toString().trim()
            val description = etCategoryDescription.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty()) {
                // Save category in shared preferences
                val sharedPrefs = getSharedPreferences("categories", MODE_PRIVATE)
                val existing = sharedPrefs.getString("list", "") ?: ""
                val newEntry = "$name: $description"
                val updated = if (existing.isEmpty()) newEntry else "$existing|||$newEntry"
                sharedPrefs.edit().putString("list", updated).apply()

                Toast.makeText(this, "Category '$name' Saved!", Toast.LENGTH_SHORT).show()
                etCategoryName.text.clear()
                etCategoryDescription.text.clear()
            } else {
                Toast.makeText(this, "Please enter both name and description.", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button logic
        btnBack.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Logout button logic
        tvLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
