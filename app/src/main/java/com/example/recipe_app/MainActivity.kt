package com.example.recipe_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.recipe_app.Fragment.FragmentRecetas2
import com.example.recipe_app.databinding.ActivityMainBinding
import com.example.recipe_app.db.DbHelper

class MainActivity : AppCompatActivity(), SearchHandler.SearchHandlerCallback {
    private lateinit var dbHelper: DbHelper
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainScreenHandler: MainScreenHandler
    private lateinit var menuLateral: MenuLateral
    private lateinit var infoextra: InfoExtra
    private lateinit var searchHandler: SearchHandler

    private val mainViewModel by viewModels<ViewModelMain>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainScreenHandler = MainScreenHandler(this, binding)
        mainScreenHandler.setupWith()

        menuLateral = MenuLateral(this)
        menuLateral.setupDrawer()

        infoextra = InfoExtra(this, binding.navView)
        dbHelper = DbHelper(this)
        val db = dbHelper.writableDatabase
        db.isOpen()

        searchHandler = SearchHandler(this, binding.etSearch, binding.rvSearchResults, this)
    }
    override fun onRecetaSelected(nombre: String, ingredientes: String, receta: String) {
        binding.viewPager.currentItem = 2  // Cambia al índice del FragmentRecetas
        mainViewModel.setNuevaReceta(ViewModelMain.Receta(
            nombre,
            ingredientes,
            receta
        ))


    }


}