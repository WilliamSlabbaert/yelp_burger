package com.example.yelpproject20.util

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.yelpproject20.R
import com.example.yelpproject20.activity.MainActivity
import com.example.yelpproject20.fragment.details

import com.example.yelpproject20.model.Restaurant
import com.google.gson.Gson

class CellClickListener {
    lateinit var detailFragment: details;
    fun onCellClickListener(activity: MainActivity, data: Restaurant?){
        detailFragment = details();
        val gson = Gson()
        val bundle = Bundle()
        bundle.putString("resto",gson.toJson(data))
        detailFragment.arguments = bundle

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper,detailFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit();
    }
}