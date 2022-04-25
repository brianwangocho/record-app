package com.example.milking

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.milking.models.MilkingData
import com.example.milking.repository.CowRepository
import com.example.milking.viewmodels.CowViewModel
import com.example.milking.viewmodels.MyViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CowDetails : AppCompatActivity() {

    private lateinit var add_milking_button: FloatingActionButton
    private lateinit var cowId  :String
    private lateinit var viewModel: CowViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cow_details)
        add_milking_button =  findViewById(R.id.floatingActionButton2)
         cowId = intent.getStringExtra("CowId").toString()
        val repository = CowRepository()
        val mainViewModelProviderFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this,mainViewModelProviderFactory).get(CowViewModel::class.java)



        add_milking_button.setOnClickListener {
            showDialog()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDialog() {
        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.milking_data_bottomsheet)
        val amount = dialog.findViewById<EditText>(R.id.amount_in_ltrs)
        val close_btn = dialog.findViewById<Button>(R.id.close)
        val submit   = dialog.findViewById<Button>(R.id.submit)

        submit?.setOnClickListener{
            val id = cowId.toInt()
            val liters = amount?.text.toString().toInt()
            var data = MilkingData(0,id,liters.toFloat())
            addMilkingSession(data)


            dialog.dismiss()

        }
        close_btn?.setOnClickListener{

            dialog.dismiss()

        }


        dialog.show()

    }


    private  fun addMilkingSession(data:MilkingData){
        viewModel.addMilkingData(data)
        viewModel.getAddCowResponse().observe(this,{


            if(it.status  == "00"){
                val snackBar = Snackbar.make(
                    View(this),
                    it.message, Snackbar.LENGTH_LONG
                )
                snackBar.setTextColor(Color.WHITE)
                snackBar.setBackgroundTint(Color.GREEN)
                snackBar.show()



            }else{

                val snackBar = Snackbar.make(
                   View(this),
                    it.message, Snackbar.LENGTH_LONG
                )
                snackBar.setTextColor(Color.WHITE)
                snackBar.setBackgroundTint(Color.RED)
                snackBar.show()


            }

        })

    }

}