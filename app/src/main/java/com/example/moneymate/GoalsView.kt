package com.example.moneymate

import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GoalsView : AppCompatActivity() {

    private lateinit var goalNameEditText: EditText
    private lateinit var maxAmount : EditText
    private lateinit var savedAmount : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_goals)

        val horizontalProgress = findViewById<ProgressBar>(R.id.horizontalProgress)
        val percentageText = findViewById<TextView>(R.id.percentageText)
        val savedText = findViewById<TextView>(R.id.savedText)
        goalNameEditText = findViewById(R.id.goalName)

        //val progressPercent = ((savedAmount / maxAmount) * 100).toInt()

        // Update horizontal progress
       // horizontalProgress.progress = progressPercent

        // Update label
        savedText.text = "R${"(savedAmount)"}"
    }
}