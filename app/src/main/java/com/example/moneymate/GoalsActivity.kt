package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GoalsActivity : AppCompatActivity() {

    private lateinit var goalNameEditText: EditText
    private lateinit var maxAmountEditText: EditText
    private lateinit var minAmountEditText: EditText
    private lateinit var addGoalsButton: Button
    private lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        // Bind views
        goalNameEditText  = findViewById(R.id.goalName)
        maxAmountEditText = findViewById(R.id.maxAmount)
        minAmountEditText = findViewById(R.id.minAmount)
        addGoalsButton    = findViewById(R.id.addGoalsButton)
        backArrow         = findViewById(R.id.backArrow)

        // Save goals logic
        addGoalsButton.setOnClickListener {
            val goalName  = goalNameEditText.text.toString().trim()
            val maxAmount = maxAmountEditText.text.toString().trim()
            val minAmount = minAmountEditText.text.toString().trim()

            if (goalName.isEmpty() || maxAmount.isEmpty() || minAmount.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save the goal to SharedPreferences
                val sharedPrefs = getSharedPreferences("categories", MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                val savedGoals = sharedPrefs.getString("goals", "")
                val updatedGoals = if (!savedGoals.isNullOrEmpty()) {
                    "$savedGoals|||$goalName"
                } else {
                    goalName
                }
                editor.putString("goals", updatedGoals)
                editor.apply()

                Toast.makeText(this, "Goals saved successfully!", Toast.LENGTH_SHORT).show()
                finish() // Return to CategorySummaryActivity
            }
        }

        // Navigate back to CategorySummaryActivity
        backArrow.setOnClickListener {
            startActivity(Intent(this, CategorySummaryActivity::class.java))
            finish()
        }
    }
}

