package com.example.milking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milking.models.Cow
import com.example.milking.repository.CowRepository
import com.example.milking.util.CowAdapter
import com.example.milking.viewmodels.CowViewModel
import com.example.milking.viewmodels.MyViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [CowsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CowsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var mycowAdapter:CowAdapter
    lateinit var recyclerView:RecyclerView
    private lateinit var viewModel: CowViewModel
    private lateinit var add_button:FloatingActionButton
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cows, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        add_button =  view.findViewById(R.id.floatingActionButton)

        add_button.setOnClickListener{
            showDialog()
        }


//        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottomSheet))
//
//        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // handle onSlide
//            }
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(
//                        context,
//                        "STATE_COLLAPSED",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(
//                        context,
//                        "STATE_EXPANDED",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(
//                        context,
//                        "STATE_DRAGGING",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(
//                        context,
//                        "STATE_SETTLING",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(
//                        context,
//                        "STATE_HIDDEN",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    else -> Toast.makeText(context, "OTHER_STATE", Toast.LENGTH_SHORT)
//                        .show()
//
//                }
//            }
//        })


        initViewModel()


        return view

    }

    private fun showDialog() {
        val dialog = context?.let { BottomSheetDialog(it) }
        if (dialog != null) {
            dialog.setContentView(R.layout.cow_bottom_sheet_form)

            val cowName = dialog.findViewById<EditText>(R.id.cow_name_text)
            val cowTag = dialog.findViewById<EditText>(R.id.cow_tag_text)
            val close_btn = dialog.findViewById<Button>(R.id.close)
            val submit   = dialog.findViewById<Button>(R.id.submit)

            submit?.setOnClickListener{

               var data =     Cow(0,cowTag?.text.toString(), cowName?.text.toString(), Date())
                //TODO: FINISH ADD COW FUNCTION TO THE SERVICE AND RESPONSE
                viewModel.addCow(data)


            }

            
            dialog.show()
        }


    }


    fun initViewModel(){
        val repository = CowRepository()


         val mainViewModelProviderFactory = MyViewModelFactory(repository)
          viewModel = ViewModelProvider(this,mainViewModelProviderFactory).get(CowViewModel::class.java)
         viewModel.getCow()

        viewModel.getlistObservable().observe(this, Observer<List<Cow>> { it
            if(it === null){
                Toast.makeText(context,"No cows found",Toast.LENGTH_LONG).show()
            }else{
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)

                    val decoration =  DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
                    addItemDecoration(decoration)
                    adapter =  CowAdapter(it as ArrayList<Cow>)

                }





            }
        })



    }






}