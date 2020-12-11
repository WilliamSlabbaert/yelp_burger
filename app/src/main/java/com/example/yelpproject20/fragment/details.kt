package com.example.yelpproject20.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.yelpproject20.R
import com.example.yelpproject20.databinding.FragmentDetailsBinding
import com.example.yelpproject20.model.Restaurant
import com.google.gson.Gson
import com.squareup.picasso.Picasso



class details : Fragment(){
    lateinit var detailsBinding: FragmentDetailsBinding
    var tempRestaurant : Restaurant? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        detailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_details,
            container,
            false)
        tempRestaurant = Gson().fromJson(arguments?.get("resto").toString(),Restaurant::class.java)

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var Is_Closed : String? = "OPEN"

        if(tempRestaurant!!.is_closed)
            Is_Closed = "CLOSED"

        detailsBinding.detailsGoogleMapsBtn.setOnClickListener {
            goToMaps(tempRestaurant!!);
        }
        detailsBinding.detailAdress.text = "Adress : " + tempRestaurant!!.adress + " \n" + "City :"+ tempRestaurant!!.city + " \n"+ "ZIP :" + tempRestaurant!!.zip

        if (tempRestaurant!!.imageUrl != "")
            Picasso.get().load(tempRestaurant!!.imageUrl).into(detailsBinding.detailImage)

        detailsBinding.detailText.text = "Currently : " + Is_Closed +"\n" +"Phone : " + tempRestaurant!!.phone + "\n" + "Rating : " + tempRestaurant!!.rating.toString() +" / 5.0"
        detailsBinding.detailBackground.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit();
        }
    }
    private fun goToMaps(business: Restaurant){
        val browserIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/search/?api=1&query=${business.latitude},${business.longitude}"))
        startActivity(browserIntent)
    }


}