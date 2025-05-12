package com.example.moneymate

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "moneyMate.db"
        private const val DATABASE_VERSION = 1

        // User table
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"

        // Category table
        private const val TABLE_CATEGORIES = "categories"
        private const val COLUMN_CATEGORY_ID = "id"
        private const val COLUMN_CATEGORY_NAME = "category_name"
        private const val COLUMN_CATEGORY_DESCRIPTION = "category_description"
        private const val COLUMN_USER_ID_FK = "user_id"

        // Expense table
        private const val TABLE_EXPENSES = "expenses"
        private const val COLUMN_EXPENSE_ID = "id"
        private const val COLUMN_EXPENSE_NAME = "expense_name"
        private const val COLUMN_EXPENSE_AMOUNT = "expense_amount"
        private const val COLUMN_EXPENSE_DATE = "expense_date"
        private const val COLUMN_CATEGORY_ID_FK = "category_id"

        // Goals table
        private const val TABLE_GOALS = "goals"
        private const val COLUMN_GOAL_ID = "id"
        private const val COLUMN_GOAL_NAME = "goal_name"
        private const val COLUMN_GOAL_AMOUNT = "goal_amount"
        private const val COLUMN_GOAL_MONTH = "goal_month"
        private const val COLUMN_USER_ID_GOAL_FK = "user_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUsers = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT UNIQUE,
                $COLUMN_PASSWORD TEXT
            )
        """

        val createCategories = """
            CREATE TABLE $TABLE_CATEGORIES (
                $COLUMN_CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CATEGORY_NAME TEXT,
                $COLUMN_CATEGORY_DESCRIPTION TEXT,
                $COLUMN_USER_ID_FK INTEGER,
                FOREIGN KEY ($COLUMN_USER_ID_FK) REFERENCES $TABLE_USERS($COLUMN_USER_ID)
            )
        """

        val createExpenses = """
            CREATE TABLE $TABLE_EXPENSES (
                $COLUMN_EXPENSE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EXPENSE_NAME TEXT,
                $COLUMN_EXPENSE_AMOUNT REAL,
                $COLUMN_EXPENSE_DATE TEXT,
                $COLUMN_CATEGORY_ID_FK INTEGER,
                FOREIGN KEY ($COLUMN_CATEGORY_ID_FK) REFERENCES $TABLE_CATEGORIES($COLUMN_CATEGORY_ID)
            )
        """

        val createGoals = """
            CREATE TABLE $TABLE_GOALS (
                $COLUMN_GOAL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_GOAL_NAME TEXT,
                $COLUMN_GOAL_AMOUNT REAL,
                $COLUMN_GOAL_MONTH TEXT,
                $COLUMN_USER_ID_GOAL_FK INTEGER,
                FOREIGN KEY ($COLUMN_USER_ID_GOAL_FK) REFERENCES $TABLE_USERS($COLUMN_USER_ID)
            )
        """

        db?.execSQL(createUsers)
        db?.execSQL(createCategories)
        db?.execSQL(createExpenses)
        db?.execSQL(createGoals)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_GOALS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // ---------------- USERS ----------------
    fun insertUser(name: String, email: String, password: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    fun getUser(email: String, password: String): Cursor? {
        val db = readableDatabase
        return db.query(
            TABLE_USERS,
            null,
            "$COLUMN_EMAIL=? AND $COLUMN_PASSWORD=?",
            arrayOf(email, password),
            null, null, null
        )
    }

    fun updateUser(id: Long, name: String, email: String, password: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        return db.update(TABLE_USERS, values, "$COLUMN_USER_ID=?", arrayOf(id.toString()))
    }

    fun deleteUser(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_USERS, "$COLUMN_USER_ID=?", arrayOf(id.toString()))
    }

    fun getAllUsers(): Cursor? {
        return readableDatabase.rawQuery("SELECT * FROM $TABLE_USERS", null)
    }

    // ---------------- CATEGORIES ----------------
    fun insertCategory(name: String, description: String, userId: Long): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CATEGORY_NAME, name)
            put(COLUMN_CATEGORY_DESCRIPTION, description)
            put(COLUMN_USER_ID_FK, userId)
        }
        return db.insert(TABLE_CATEGORIES, null, values)
    }

    fun getCategories(userId: Long): Cursor? {
        return readableDatabase.query(
            TABLE_CATEGORIES,
            null,
            "$COLUMN_USER_ID_FK=?",
            arrayOf(userId.toString()),
            null, null, null
        )
    }

    fun updateCategory(id: Long, name: String, description: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CATEGORY_NAME, name)
            put(COLUMN_CATEGORY_DESCRIPTION, description)
        }
        return db.update(TABLE_CATEGORIES, values, "$COLUMN_CATEGORY_ID=?", arrayOf(id.toString()))
    }

    fun deleteCategory(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_CATEGORIES, "$COLUMN_CATEGORY_ID=?", arrayOf(id.toString()))
    }

    // ---------------- EXPENSES ----------------
    fun insertExpense(name: String, amount: Double, date: String, categoryId: Long): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EXPENSE_NAME, name)
            put(COLUMN_EXPENSE_AMOUNT, amount)
            put(COLUMN_EXPENSE_DATE, date)
            put(COLUMN_CATEGORY_ID_FK, categoryId)
        }
        return db.insert(TABLE_EXPENSES, null, values)
    }

    fun getExpenses(categoryId: Long): Cursor? {
        return readableDatabase.query(
            TABLE_EXPENSES,
            null,
            "$COLUMN_CATEGORY_ID_FK=?",
            arrayOf(categoryId.toString()),
            null, null, null
        )
    }

    fun updateExpense(id: Long, name: String, amount: Double, date: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EXPENSE_NAME, name)
            put(COLUMN_EXPENSE_AMOUNT, amount)
            put(COLUMN_EXPENSE_DATE, date)
        }
        return db.update(TABLE_EXPENSES, values, "$COLUMN_EXPENSE_ID=?", arrayOf(id.toString()))
    }

    fun deleteExpense(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_EXPENSES, "$COLUMN_EXPENSE_ID=?", arrayOf(id.toString()))
    }

    // ---------------- GOALS ----------------
    fun insertGoal(goalName: String, goalAmount: Double, goalMonth: String, userId: Long): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_GOAL_NAME, goalName)
            put(COLUMN_GOAL_AMOUNT, goalAmount)
            put(COLUMN_GOAL_MONTH, goalMonth)
            put(COLUMN_USER_ID_GOAL_FK, userId)
        }
        return db.insert(TABLE_GOALS, null, values)
    }

    fun getGoals(userId: Long): Cursor? {
        return readableDatabase.query(
            TABLE_GOALS,
            null,
            "$COLUMN_USER_ID_GOAL_FK=?",
            arrayOf(userId.toString()),
            null, null, null
        )
    }

    fun updateGoal(id: Long, name: String, amount: Double, month: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_GOAL_NAME, name)
            put(COLUMN_GOAL_AMOUNT, amount)
            put(COLUMN_GOAL_MONTH, month)
        }
        return db.update(TABLE_GOALS, values, "$COLUMN_GOAL_ID=?", arrayOf(id.toString()))
    }

    fun deleteGoal(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_GOALS, "$COLUMN_GOAL_ID=?", arrayOf(id.toString()))
    }

    // ---------------- AGGREGATE FUNCTIONS ----------------
    fun getTotalExpensesForCategory(categoryId: Long): Double {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT SUM($COLUMN_EXPENSE_AMOUNT) FROM $TABLE_EXPENSES WHERE $COLUMN_CATEGORY_ID_FK = ?",
            arrayOf(categoryId.toString())
        )
        val total = if (cursor.moveToFirst()) cursor.getDouble(0) else 0.0
        cursor.close()
        return total
    }

    fun getTotalExpensesForUser(userId: Long): Double {
        val db = readableDatabase
        val cursor = db.rawQuery(
            """
            SELECT SUM(e.$COLUMN_EXPENSE_AMOUNT)
            FROM $TABLE_EXPENSES e
            JOIN $TABLE_CATEGORIES c ON e.$COLUMN_CATEGORY_ID_FK = c.$COLUMN_CATEGORY_ID
            WHERE c.$COLUMN_USER_ID_FK = ?
            """.trimIndent(),
            arrayOf(userId.toString())
        )
        val total = if (cursor.moveToFirst()) cursor.getDouble(0) else 0.0
        cursor.close()
        return total
    }
    fun getExpensesForCategory(categoryId: Long): Cursor {
        val db = readableDatabase
        return db.rawQuery(
            "SELECT * FROM expenses WHERE category_id = ?",
            arrayOf(categoryId.toString())
        )
    }
}
