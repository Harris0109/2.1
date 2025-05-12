package com.example.moneymate

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUpText = findViewById<TextView>(R.id.signupTitle)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)

        // Handle SignUp TextView click
        signUpText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Create an instance of DatabaseHelper
                val dbHelper = DatabaseHelper(this)

                // Check if the user exists in the database
                val cursor: Cursor? = dbHelper.getUser(email, password)

                if (cursor != null && cursor.moveToFirst()) {
                    // User found in the database
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                    // Redirect to Welcome Activity with the user's email
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra("user_name", email)  // Passing email as user name for now
                    startActivity(intent)
                    finish()
                } else {
                    // User does not exist in the database, prompt to sign up
                    Toast.makeText(this, "User not found. Please sign up", Toast.LENGTH_SHORT).show()

                    // Optionally, redirect to the sign-up activity
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                // Close cursor after use to prevent memory leaks
                cursor?.close()
            }
        }
    }
}
