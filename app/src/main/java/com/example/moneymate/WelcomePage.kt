package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val okButton = findViewById<Button>(R.id.okButton)

        // Get the user name from the previous screen or use "User" as fallback if not provided
        val userName = intent.getStringExtra("user_name").orEmpty().takeIf { it.isNotBlank() } ?: "User"

        // Set the welcome message
        welcomeText.text = "Welcome to Money Mate, $userName"

        // Set the action for the OK button
        okButton.setOnClickListener {
            // Navigate to BudgetActivity and pass the user name
            val intent = Intent(this, BudgetActivity::class.java)
            intent.putExtra("user_name", userName) // Pass the name to the next activity
            startActivity(intent)
            finish() // Close WelcomeActivity to prevent the user from going back to it
        }
    }
}

