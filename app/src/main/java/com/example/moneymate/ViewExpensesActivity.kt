package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses) // <-- match with your XML file name

        val backArrow: Button = findViewById(R.id.backArrow)
        val logout: TextView = findViewById(R.id.logout)
        val expenseListView: ListView = findViewById(R.id.expenseListView)

        val expenseList = mutableListOf<String>()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, expenseList)
        expenseListView.adapter = adapter

        // Clear any pre-existing data (if needed)
        expenseList.clear()
        adapter.notifyDataSetChanged()

        // Set up Back Arrow click listener
        backArrow.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java) // Navigate to BudgetActivity
            startActivity(intent)
            finish() // Optional: Finish current activity to prevent going back to ViewExpensesActivity
        }

        // Set up Logout click listener
        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to prevent going back to ViewExpensesActivity
        }
    }
}

