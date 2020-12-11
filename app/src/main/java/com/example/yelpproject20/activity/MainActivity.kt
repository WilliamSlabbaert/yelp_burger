package com.example.yelpproject20.activity

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yelpproject20.util.BusinessAdapter
import com.example.yelpproject20.R
import com.example.yelpproject20.fragment.settings
import com.example.yelpproject20.model.Restaurant
import com.example.yelpproject20.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File


class MainActivity : AppCompatActivity() ,LifecycleOwner{

    var viewmodel : ViewModel? = null;
    var directory : String?= null;
    lateinit var settingsFragment: settings;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        directory = filesDir.absolutePath.toString() + "/businesses.txt";
        try {
            viewmodel = ViewModelProvider(this).get(MainViewModel::class.java);
            allowRequestRetroFit()
        }catch (e: ArithmeticException){
            showToast("ERROR");
            Log.d("Error", e.toString());
        }
        onClickNav();
    }

    override fun onStart() {
        super.onStart()
        allowRequestRetroFit();
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Goodnight");
    }

    fun allowRequestRetroFit(){
        if(isConnected(this)){
            (viewmodel as MainViewModel).setData(directory.toString());
            if(File(directory).isFile)
                setupDataRecyclerView((viewmodel as MainViewModel).getData())
            else
                showToast("Error")
        }
        else{
            if(File(directory).isFile)
                setupDataRecyclerView((viewmodel as MainViewModel).getData())
            else
                showToast("No internet")
        }
    }
    fun onClickNav(){
        (findViewById(R.id.bottom_navigation) as BottomNavigationView).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_settings -> {
                    settingsFragment = settings();
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_wrapper,settingsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit(); }
                R.id.nav_home -> {
                    allowRequestRetroFit();
                    supportFragmentManager.beginTransaction().remove(settingsFragment).commit(); }
            }
            true
        }
    }

    fun setupDataRecyclerView(businessData: List<Restaurant>?){
        val recycler_view : RecyclerView = findViewById(R.id.recycler_view);
        recycler_view.adapter =  BusinessAdapter(this,businessData);
        recycler_view.layoutManager = LinearLayoutManager(this);
        recycler_view.setHasFixedSize(true);
    }
    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun isConnected(context : Context) :Boolean{
        var connectivityManager : ConnectivityManager? = null;
        connectivityManager = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager;
        if(connectivityManager != null){
            var info = connectivityManager!!.activeNetworkInfo
            if(info != null){
                if(info.state == NetworkInfo.State.CONNECTED ){
                    return true;
                }
            }
        }
        return false;
    }
}