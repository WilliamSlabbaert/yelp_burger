package com.example.yelpproject20.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.yelpproject20.R
import com.example.yelpproject20.Repo.DbSql
import com.example.yelpproject20.databinding.ActivityLoginBinding
import com.example.yelpproject20.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var dataBindingUtil:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingUtil = DataBindingUtil.setContentView(this,R.layout.activity_login)


        dataBindingUtil.signUpBtn.setOnClickListener {
            if (registerAccount())
                showToast("SUCCESS")
            else
                showToast("INVALID ACCOUNT")
            resetInputs()
        }
        dataBindingUtil.loginBtn.setOnClickListener{
            if(loginAccount()){
                showToast("WELCOME")
                val intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
            }
            else
                showToast("WRONG EMAIL OR PASSWORD")
            resetInputs()
        }
    }
    private fun getInsert() : User {
        val email = dataBindingUtil.emailInput.text.toString()
        val pass = dataBindingUtil.passwordInput.text.toString()
        return User(email,pass)
    }
    private fun resetInputs(){
        dataBindingUtil.emailInput.text = null
        dataBindingUtil.passwordInput.text = null
    }
    private fun registerAccount(): Boolean {
        return DbSql(this).registerAccount(getInsert())
    }
    private fun loginAccount(): Boolean {
        return DbSql(this).loginAccount(getInsert())
    }
    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}