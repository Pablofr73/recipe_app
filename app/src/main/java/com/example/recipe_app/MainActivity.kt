package com.example.recipe_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainScreenHandler: MainScreenHandler
    private lateinit var menuLateral: MenuLateral
    private lateinit var infoextra: InfoExtra
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainScreenHandler = MainScreenHandler(this, binding)
        mainScreenHandler.setupWith()

        menuLateral = MenuLateral(this) // Inicializa MenuLateral con la instancia actual de MainActivity
        menuLateral.setupDrawer()

        infoextra = InfoExtra(this, binding.navView)
    }
}