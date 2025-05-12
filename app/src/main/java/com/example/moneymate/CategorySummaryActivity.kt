package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategorySummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_summary)

        val backArrow: ImageView = findViewById(R.id.backArrow)
        val logout: TextView = findViewById(R.id.tvLogout)
        val placeholder: TextView = findViewById(R.id.placeholderMessage)
        val categoryContainer: LinearLayout = findViewById(R.id.categoryContainer)

        // Set back button functionality
        backArrow.setOnClickListener { finish() }

        // Set logout functionality
        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Retrieve the saved categories and goals from SharedPreferences
        val sharedPrefs = getSharedPreferences("categories", MODE_PRIVATE)
        val savedCategories = sharedPrefs.getString("list", "")
        val savedGoals = sharedPrefs.getString("goals", "")

        // Check if there are saved categories
        if (!savedCategories.isNullOrEmpty()) {
            // Hide placeholder message if categories are found
            placeholder.visibility = View.GONE
            // Split the saved list into individual categories
            val categories = savedCategories.split("|||")
            for (item in categories) {
                val textView = TextView(this)
                textView.text = item
                textView.textSize = 16f
                textView.setPadding(0, 10, 0, 10)
                textView.setBackgroundResource(R.drawable.category_background)  // Optional: Add background to each category
                categoryContainer.addView(textView)
            }
        }

        // Check if there are saved goals
        if (!savedGoals.isNullOrEmpty()) {
            // Hide placeholder message if goals are found
            placeholder.visibility = View.GONE
            // Split the saved goals into individual items
            val goals = savedGoals.split("|||")
            for (goal in goals) {
                val textView = TextView(this)
                textView.text = goal
                textView.textSize = 16f
                textView.setPadding(0, 10, 0, 10)
                textView.setBackgroundResource(R.drawable.goal_background)  // Optional: Add background to each goal
                categoryContainer.addView(textView)
            }
        }

        // If no categories or goals, show placeholder message
        if (savedCategories.isNullOrEmpty() && savedGoals.isNullOrEmpty()) {
            placeholder.visibility = View.VISIBLE
        }
    }
}

