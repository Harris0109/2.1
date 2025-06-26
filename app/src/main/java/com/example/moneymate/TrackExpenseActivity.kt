package com.example.moneymate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TrackExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_expense)

        val dateInput = findViewById<EditText>(R.id.dateInput)
        val startTimeInput = findViewById<EditText>(R.id.startTimeInput)
        val endTimeInput = findViewById<EditText>(R.id.endTimeInput)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        backArrow.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        dateInput.setOnClickListener { showDatePicker(dateInput) }
        startTimeInput.setOnClickListener { showTimePicker(startTimeInput) }
        endTimeInput.setOnClickListener { showTimePicker(endTimeInput) }

        val uploadButton = findViewById<Button>(R.id.uploadSlipButton)
        uploadButton.setOnClickListener {
            Toast.makeText(this, "Upload functionality coming soon!", Toast.LENGTH_SHORT).show()
        }

        val submitBtn = findViewById<Button>(R.id.submitExpenseButton)
        submitBtn.setOnClickListener {
            Toast.makeText(this, "Expense submitted!", Toast.LENGTH_SHORT).show()

            val prefs = getSharedPreferences("com.example.moneymate", MODE_PRIVATE)
            val expenseCount = prefs.getInt("expense_count", 0) + 1
            prefs.edit().putInt("expense_count", expenseCount).apply()

            if (expenseCount % 5 == 0) { // Every 5th expense
                val intent = Intent(this, RewardActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, BudgetActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }

    private fun showDatePicker(editText: EditText) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, y, m, d ->
            editText.setText("$d/${m + 1}/$y")
        }, year, month, day).show()
    }

    private fun showTimePicker(editText: EditText) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, h, m ->
            editText.setText(String.format("%02d:%02d", h, m))
        }, hour, minute, true).show()
    }
}
