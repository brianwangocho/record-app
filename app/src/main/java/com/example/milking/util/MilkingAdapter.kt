package com.example.milking.util

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.milking.CowDetails
import com.example.milking.R
import com.example.milking.models.Cow
import com.example.milking.models.MilkingData
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MilkingAdapter(private val milkingdata:ArrayList<MilkingData>): RecyclerView.Adapter<MilkingAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkingAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.milking_list,
            parent,false)

        return MilkingAdapter.MyViewHolder(itemView)

    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MilkingAdapter.MyViewHolder, position: Int) {

        val currentItem =  milkingdata[position]
        holder.amount.text  =  currentItem.amount.toString()
       Picasso.get().load(R.drawable.icons8_cow_48).into(holder.cowPic)
        //holder.time.text = currentItem.createdOn.toString()
        if(currentItem.createdOn != null){
//
            var time  = currentItem.createdOn
            val dateFormat = "dd-MM-yyyy"
            val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
            val calendar = Calendar.getInstance()

            calendar.timeInMillis = time.toLong()
            var xyz = formatter.format(calendar.time)
            holder.time.text =xyz

        }else{
            holder.time.text  = "not set"

        }

        holder.id.text  = currentItem.id.toString()
    }

    override fun getItemCount(): Int {

        return milkingdata.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

          val cowPic : ImageView = itemView.findViewById(R.id.imageView)
        val amount : TextView = itemView.findViewById(R.id.cow_name)
        val time: TextView = itemView.findViewById(R.id.tag_number)
        val id: TextView = itemView.findViewById(R.id.cow_id)



        init{



        }
    }


}