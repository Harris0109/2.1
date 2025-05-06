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

        // Back navigation
        backArrow.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Date Picker
        dateInput.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                dateInput.setText("$d/${m+1}/$y")
            }, year, month, day)
            dpd.show()
        }

        // Time Pickers
        fun showTimePicker(editText: EditText) {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, h, m ->
                editText.setText(String.format("%02d:%02d", h, m))
            }, hour, minute, true).show()
        }

        startTimeInput.setOnClickListener { showTimePicker(startTimeInput) }
        endTimeInput.setOnClickListener { showTimePicker(endTimeInput) }

        // Upload Slip Button
        val uploadButton = findViewById<Button>(R.id.uploadSlipButton)
        uploadButton.setOnClickListener {
            Toast.makeText(this, "Upload functionality coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Submit Button
        val submitBtn = findViewById<Button>(R.id.submitExpenseButton)
        submitBtn.setOnClickListener {
            Toast.makeText(this, "Expense submitted!", Toast.LENGTH_SHORT).show()
            // You can store or send the data here
        }
    }
}
