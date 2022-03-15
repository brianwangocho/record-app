package com.example.milking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.milking.repository.CowRepository
import com.example.milking.viewmodels.CowViewModel
import com.example.milking.viewmodels.MyViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:CowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navigationController)
        val appconfigObject = AppBarConfiguration(setOf(R.id.dash_fragment,
                                                        R.id.cowsFragment,
                                                        R.id.milkingFragment))
        setupActionBarWithNavController(navigationController,appconfigObject)
//        val repository = CowRepository()
//        val  viewModelFactory = MyViewModelFactory(repository)
//        viewModel = ViewModelProvider(this,viewModelFactory).get(CowViewModel::class.java)
        //viewModel.getCow()
    }
}