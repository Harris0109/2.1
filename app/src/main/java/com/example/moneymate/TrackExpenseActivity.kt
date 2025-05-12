package com.example.moneymate

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TrackExpenseActivity : AppCompatActivity() {

    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var expenseDateEditText: EditText
    private lateinit var addExpenseButton: Button
    private lateinit var expenseListView: ListView

    private val expenseList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbHelper: DatabaseHelper

    private var categoryId: Long = 1L // Replace with dynamic ID if needed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_expense)

        expenseNameEditText = findViewById(R.id.expenseNameEditText)
        expenseAmountEditText = findViewById(R.id.expenseAmountEditText)
        expenseDateEditText = findViewById(R.id.expenseDateEditText)
        addExpenseButton = findViewById(R.id.addExpenseButton)
        expenseListView = findViewById(R.id.expenseListView)

        dbHelper = DatabaseHelper(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, expenseList)
        expenseListView.adapter = adapter

        loadExpensesFromDatabase()

        addExpenseButton.setOnClickListener {
            val name = expenseNameEditText.text.toString().trim()
            val amountStr = expenseAmountEditText.text.toString().trim()
            val date = expenseDateEditText.text.toString().trim()

            if (name.isEmpty() || amountStr.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "Enter a valid number for amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = dbHelper.insertExpense(name, amount, date, categoryId)
            if (result != -1L) {
                val expenseItem = "$date - $name: R$amount"
                expenseList.add(expenseItem)
                adapter.notifyDataSetChanged()

                expenseNameEditText.text.clear()
                expenseAmountEditText.text.clear()
                expenseDateEditText.text.clear()

                Toast.makeText(this, "Expense saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save expense", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadExpensesFromDatabase() {
        val cursor = dbHelper.getExpensesForCategory(categoryId)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("expense_name"))
                val amount = cursor.getDouble(cursor.getColumnIndexOrThrow("expense_amount"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("expense_date"))
                val expenseItem = "$date - $name: R$amount"
                expenseList.add(expenseItem)
            } while (cursor.moveToNext())
        }
        cursor.close()
        adapter.notifyDataSetChanged()
    }
}
