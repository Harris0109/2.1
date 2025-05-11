package com.example.moneymate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TrackExpenseActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private val IMAGE_PICK_CODE = 1001
    private var selectedSlipUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_expense)

        dbHelper = DatabaseHelper(this)

        val dateInput = findViewById<EditText>(R.id.dateInput)
        val startTimeInput = findViewById<EditText>(R.id.startTimeInput)
        val endTimeInput = findViewById<EditText>(R.id.endTimeInput)
        val uploadButton = findViewById<Button>(R.id.uploadSlipButton)
        val submitBtn = findViewById<Button>(R.id.submitExpenseButton)
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val logout = findViewById<TextView>(R.id.logout)

        // Date Picker
        dateInput.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                dateInput.setText("$d/${m + 1}/$y")
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

        // Upload Slip
        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // Submit Button
        submitBtn.setOnClickListener {
            val date = dateInput.text.toString()
            val startTime = startTimeInput.text.toString()
            val endTime = endTimeInput.text.toString()

            if (date.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty()) {
                val result = dbHelper.insertExpense(date, startTime, endTime, selectedSlipUri)
                if (result != -1L) {
                    Toast.makeText(this, "Expense submitted successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to submit expense.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigation
        backArrow.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            selectedSlipUri = imageUri.toString()
            Toast.makeText(this, "Slip selected!", Toast.LENGTH_SHORT).show()
        }
    }
}
