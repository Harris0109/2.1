package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.TextView

class GoalsActivity : AppCompatActivity() {

    private lateinit var goalNameEditText: EditText
    private lateinit var maxAmountEditText: EditText
    private lateinit var minAmountEditText: EditText
    private lateinit var addGoalsButton: Button
    private lateinit var backArrow: ImageView
    private lateinit var logoutTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)  // Make sure this matches your XML file name

        // Link views with their IDs
        goalNameEditText = findViewById(R.id.goalName)
        maxAmountEditText = findViewById(R.id.maxAmount)
        minAmountEditText = findViewById(R.id.minAmount)
        addGoalsButton = findViewById(R.id.addGoalsButton)
        backArrow = findViewById(R.id.backArrow)
        logoutTextView = findViewById(R.id.logout)

        // Add Goals button logic
        addGoalsButton.setOnClickListener {
            val goalName = goalNameEditText.text.toString()
            val maxAmount = maxAmountEditText.text.toString()
            val minAmount = minAmountEditText.text.toString()

            if (goalName.isEmpty() || maxAmount.isEmpty() || minAmount.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Goals saved successfully!", Toast.LENGTH_SHORT).show()
                // Optional: Save to DB or pass to another activity here
                finish() // Go back to dashboard
            }
        }

        // Back arrow returns to dashboard
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Log out text returns to logo
        val logoutText = findViewById<TextView>(R.id.logout)
        logoutText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

    }
}
