package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses) // <-- must match your XML file name

        val backArrow: ImageView = findViewById(R.id.backArrow)
        val logout: TextView = findViewById(R.id.logout)

        backArrow.setOnClickListener {
            finish()
        }

        logout.setOnClickListener {
            // Redirect to login or welcome screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

