package com.example.yelpproject20.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yelpproject20.R
import com.example.yelpproject20.activity.MainActivity
import com.example.yelpproject20.databinding.CardItemBinding
import com.example.yelpproject20.model.Restaurant
import com.squareup.picasso.Picasso

class BusinessAdapter(private val activity: MainActivity, private val datalist: List<Restaurant>?) :RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>(){

    class BusinessViewHolder(private val activity: MainActivity, private var binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun bindData(currentItem: Restaurant?) {
            var Is_Closed : String? = "OPEN"
            if (currentItem?.imageUrl != null || currentItem?.imageUrl != "") {
                Picasso.get().load(currentItem?.imageUrl).into(binding.cardImage) }

            binding.txtNameOfRestaurant.text = currentItem?.name
            binding.txtRatingScore.text =  "${currentItem?.rating}/5.0"
            binding.ratingImage.setImageResource(R.mipmap.star)

            if(currentItem!!.is_closed)
                Is_Closed = "CLOSED"
            binding.txtDescription.text = Is_Closed

            binding.card.setOnClickListener{
                CellClickListener().onCellClickListener(activity,currentItem);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BusinessViewHolder(activity,binding)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val currentItem = datalist?.get(position)
        holder.bindData(currentItem)
    }
    override fun getItemCount(): Int = datalist?.size!!
}