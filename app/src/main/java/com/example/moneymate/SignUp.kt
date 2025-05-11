package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.signupEmailInput)
        val passwordInput = findViewById<EditText>(R.id.signupPasswordInput)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val loginText = findViewById<TextView>(R.id.loginText)

        // Create an instance of DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        signupButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Insert the user into the database
                val result = dbHelper.insertUser(name, email, password)

                if (result != -1L) {
                    Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()

                    // Redirect to Welcome Activity with the user's name
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra("user_name", name)  // Send the name entered during signup
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error signing up", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
