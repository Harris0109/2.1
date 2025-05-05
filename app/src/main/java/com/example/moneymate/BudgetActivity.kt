package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BudgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        // Set Welcome Text
        val userName = "Franklin Ngangu"  // Replace with actual signed in user's name later
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome, $userName!"

        // Button to open CreateCategoryActivity
        val btnCreateCategory = findViewById<Button>(R.id.btnCreateCategory)
        btnCreateCategory.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

        // Button to open GoalsActivity
        val btnSetMonthlyGoals = findViewById<Button>(R.id.btnSetMonthlyGoals)
        btnSetMonthlyGoals.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            startActivity(intent)
        }
    }
}


