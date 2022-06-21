package com.example.milking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milking.models.Cow
import com.example.milking.models.MilkingData
import com.example.milking.repository.CowRepository
import com.example.milking.util.CowAdapter
import com.example.milking.util.MilkingAdapter
import com.example.milking.viewmodels.CowViewModel
import com.example.milking.viewmodels.MyViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [MilkingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MilkingFragment : Fragment() {
    lateinit var mycowAdapter: MilkingAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initViewModel()
        val view = inflater.inflate(R.layout.fragment_milking, container, false)
        recyclerView = view.findViewById(R.id.milking_collected_list)
        return view
    }

    fun initViewModel(){
        val repository = CowRepository()


        val mainViewModelProviderFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this,mainViewModelProviderFactory).get(CowViewModel::class.java)
        viewModel.getMilkingData()

        viewModel.getMilkingListObservable().observe(this, { it
            if(it === null){
                Toast.makeText(context,"Data  FOUND", Toast.LENGTH_LONG).show()
            }else{
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)

                    val decoration =  DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    addItemDecoration(decoration)
                    adapter =  MilkingAdapter(it as ArrayList<MilkingData>)

                }





            }
        })



    }


}