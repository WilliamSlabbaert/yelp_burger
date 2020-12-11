package com.example.yelpproject20.Repo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.yelpproject20.model.User


class DbSql(context: Context) : SQLiteOpenHelper(context,"User_Database",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE user_table (Email VARCHAR(256),Password VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS user_table"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun registerAccount(user: User): Boolean {
        val db = this.writableDatabase
        val userList = ArrayList<User>()
        val tempEmail = user.email;
        if(user.email.isNullOrEmpty() || user.pass.isNullOrEmpty())
            return false

        var result = db.rawQuery("SELECT * FROM user_table WHERE Email = '$tempEmail'", null)
        if (result.moveToNext()) {
            do {
                userList.add(User(result.getString(result.getColumnIndex("Email")),
                    result.getString(result.getColumnIndex("Password"))))
            } while (result.moveToNext())
        }
        result.close()
        if (userList.size == 0) {
            var cv = ContentValues()
            cv.put("Email", user.email)
            cv.put("Password", user.pass)
            db.insert("user_table", null, cv)
            return true
        }
        return false
    }

    fun loginAccount(user: User): Boolean {
        val db = this.writableDatabase
        val tempEmail = user.email;
        val tempPass = user.pass;

        var result = db.rawQuery("SELECT * FROM user_table WHERE Email = '$tempEmail' AND Password = '$tempPass'", null)

        if (result.count == 0)
            return false
        return true
    }
}