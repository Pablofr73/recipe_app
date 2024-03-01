package com.example.recipe_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: CategoryAdapter
    private var isExpanded = false // Controla el estado de expansión de las categorías
    data class Category(
        val name: String,
        val icon: Int // Este es un ID de recurso drawable
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ejemplo de datos para las categorías
        val categories = listOf(
            Category("Mexicana", R.drawable.chili),
            Category("Italiana", R.drawable.italian),
            Category("Japonesa", R.drawable.japan),
            Category("Res", R.drawable.cow),
            Category("Cerdo", R.drawable.pig),
            Category("Pollo", R.drawable.chicken),
            Category("Pescado", R.drawable.fish),
            Category("Conejo", R.drawable.rabbit),
            Category("Cordero", R.drawable.sheep),
            Category("Mediterranea", R.drawable.octopus),
            Category("Patatas", R.drawable.potato),
            Category("Vegana", R.drawable.vegan),
            Category("Comida Rapida", R.drawable.fastfood),
            Category("Desayuno", R.drawable.breakfast),
            Category("Postres", R.drawable.desserts),
            Category("Panadería", R.drawable.bread),
            Category("Sopas", R.drawable.soup),
            Category("Bebidas", R.drawable.frappe),
        )

        val recyclerViewCategories: RecyclerView = findViewById(R.id.recycler_view_categories)
        recyclerViewCategories.layoutManager = GridLayoutManager(this, 3)
        categoryAdapter = CategoryAdapter(categories.take(6)) // Muestra solo 6 categorías inicialmente
        recyclerViewCategories.adapter = categoryAdapter

        val tvVerMas: TextView = findViewById(R.id.tv_ver_mas)
        tvVerMas.setOnClickListener {
            // Cambia la lista de categorías y actualiza el texto y la flecha del TextView aquí...
            if (!isExpanded) {
                categoryAdapter.categories = categories
                categoryAdapter.notifyDataSetChanged()
                tvVerMas.text = "Ver Menos"
                isExpanded = true
            } else {
                categoryAdapter.categories = categories.take(6)
                categoryAdapter.notifyDataSetChanged()
                tvVerMas.text = "Ver Más"
                isExpanded = false
            }
        }
    }
}