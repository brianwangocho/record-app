package com.example.milking.util

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.milking.CowDetails
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
        holder.id.text  = currentItem.id.toString()

    }

    override fun getItemCount(): Int {

        return cowlist.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val cowPic : ImageView = itemView.findViewById(R.id.imageView)
        val cowName : TextView = itemView.findViewById(R.id.cow_name)
        val tagNumber:TextView = itemView.findViewById(R.id.tag_number)
        val id:TextView = itemView.findViewById(R.id.cow_id)



        init{
            itemView.setOnClickListener{
                Toast.makeText(itemView.context, id.text, Toast.LENGTH_SHORT).show()
                val intent = Intent (itemView.context, CowDetails::class.java)
                intent.putExtra("CowId",id.text)
                itemView.context?.startActivity(intent)
            }


        }
    }

}