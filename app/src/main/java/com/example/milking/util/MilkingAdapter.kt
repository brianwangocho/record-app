package com.example.milking.util

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
import com.example.milking.models.MilkingData

class MilkingAdapter(private val milkingdata:ArrayList<MilkingData>): RecyclerView.Adapter<MilkingAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkingAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.milking_list,
            parent,false)

        return MilkingAdapter.MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MilkingAdapter.MyViewHolder, position: Int) {

        val currentItem =  milkingdata[position]
        holder.amount.text  =  currentItem.amount.toString()
        //holder.time.text = currentItem.createdOn.toString()
        holder.id.text  = currentItem.id.toString()
    }

    override fun getItemCount(): Int {

        return milkingdata.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

//        val cowPic : ImageView = itemView.findViewById(R.id.imageView)
        val amount : TextView = itemView.findViewById(R.id.cow_name)
        val time: TextView = itemView.findViewById(R.id.tag_number)
        val id: TextView = itemView.findViewById(R.id.cow_id)



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