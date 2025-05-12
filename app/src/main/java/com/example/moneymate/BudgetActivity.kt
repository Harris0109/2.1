package com.example.moneymate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BudgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        // Get the user's name from the Intent
        val userName = intent.getStringExtra("user_name") ?: ""

        // Set Welcome Text
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome, $userName!"

        // Button to open CreateCategoryActivity
        val btnCreateCategory = findViewById<Button>(R.id.btnCreateCategory)
        btnCreateCategory.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

        // Button to open TrackExpenseActivity
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, TrackExpenseActivity::class.java)
            startActivity(intent)
        }

        // Button to open GoalsActivity
        val btnSetMonthlyGoals = findViewById<Button>(R.id.btnSetMonthlyGoals)
        btnSetMonthlyGoals.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            startActivity(intent)
        }

        // Button to open ViewExpensesActivity
        val btnViewExpenses = findViewById<Button>(R.id.btnViewExpenses)
        btnViewExpenses.setOnClickListener {
            val intent = Intent(this, ViewExpensesActivity::class.java)
            startActivity(intent)
        }

        // Button to open CategorySummaryActivity
        val btnCategorySummary = findViewById<Button>(R.id.btnCategorySummary)
        btnCategorySummary.setOnClickListener {
            val intent = Intent(this, CategorySummaryActivity::class.java)
            startActivity(intent)
        }

        // Logout functionality
        val logoutText = findViewById<TextView>(R.id.logoutText)
        logoutText.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {
        clearUserSession()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clearUserSession() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
