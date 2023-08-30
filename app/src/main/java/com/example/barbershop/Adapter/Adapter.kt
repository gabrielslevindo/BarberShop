package com.example.barbershop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.data.Services
import com.example.barbershop.databinding.ServicesItemBinding

class ServicesAdapter(private val context: Context,
      private val ServicesList:MutableList<Services>):
      RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val view = ServicesItemBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ServicesViewHolder(view)


    }

    override fun getItemCount() = ServicesList.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
            holder.imgService.setImageResource(ServicesList[position].img!!)
            holder.txtService.text = ServicesList[position].name
    }



    inner class ServicesViewHolder(binding: ServicesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgService = binding.imgService
        val txtService = binding.tvItemServices

    }





}


