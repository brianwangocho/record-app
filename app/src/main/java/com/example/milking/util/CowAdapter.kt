package com.example.milking.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.milking.R


import com.example.milking.models.Cow

class CowAdapter(private val cowlist:ArrayList<Cow>):RecyclerView.Adapter<CowAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(
           R.layout.cow_list,
                                                                parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =  cowlist[position]
        holder.cowName.text  =  currentItem.name
        holder.tagNumber.text = currentItem.tag
    }

    override fun getItemCount(): Int {

        return cowlist.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val cowPic : ImageView = itemView.findViewById(R.id.imageView)
        val cowName : TextView = itemView.findViewById(R.id.cow_name)
        val tagNumber:TextView = itemView.findViewById(R.id.tag_number)
    }

}