package com.example.yelpproject20.activity

import android.app.Application
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yelpproject20.R
import com.example.yelpproject20.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    lateinit var handler : Handler
    var viewmodel : SplashViewModel? = null;
    override fun onStart() {
        super.onStart()
        viewmodel = ViewModelProvider(this,ViewModelFactory(this.application)).get(SplashViewModel::class.java);
        (viewmodel as SplashViewModel).setData(filesDir.absolutePath.toString() + "/businesses.txt")
        setContentView(R.layout.activity_splash)
        handler = Handler()
        handler.postDelayed({
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
    internal class ViewModelFactory(val app: Application) : ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) return SplashViewModel(app) as T
            throw Exception("Unknown ViewModel class")
        }
    }

}
