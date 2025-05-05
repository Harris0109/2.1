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

        // Optional: get the user name from previous screen
        val userName = intent.getStringExtra("user_name") ?: "User"
        welcomeText.text = "Welcome to Money Mate, $userName"

        okButton.setOnClickListener {
            // Navigate to the main budget app screen
            val intent = Intent(this, BudgetActivity::class.java) // or MainActivity
            startActivity(intent)
            finish()
        }
    }
}

