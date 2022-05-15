package com.example.milking

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
import com.google.android.material.snackbar.Snackbar
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
        setHasOptionsMenu(true)



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


            addCow(cowName?.text.toString(),cowTag?.text.toString())
                dialog.dismiss()

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
                    mycowAdapter = CowAdapter(it as ArrayList<Cow>)
                    adapter =  mycowAdapter


                }





            }
        })



    }

    fun addCow(name:String,tag:String){
        var data =     Cow(0,name,tag, Date())
        //TODO: FINISH ADD COW FUNCTION TO THE SERVICE AND RESPONSE - DONE
        //TODO( MAKE CHECKS SO USERS MAY  NOT  POST NULL IN INPUTS)

        viewModel.addCow(data)
        viewModel.getAddCowResponse().observe(this,{


            if(it.status  == "00"){
                val snackBar = Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    it.message, Snackbar.LENGTH_LONG
                )
                snackBar.setTextColor(Color.WHITE)
                snackBar.setBackgroundTint(Color.GREEN)
                snackBar.show()



            }else{

                val snackBar = Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    it.message, Snackbar.LENGTH_LONG
                )
                snackBar.setTextColor(Color.WHITE)
                snackBar.setBackgroundTint(Color.RED)
                snackBar.show()


            }

        })



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_cows_recyclerview,menu)

        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mycowAdapter.filter.filter(p0)
                return false;

            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false;
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }






}