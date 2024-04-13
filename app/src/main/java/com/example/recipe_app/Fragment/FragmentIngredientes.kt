package com.example.recipe_app.Fragment

import IngredientesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.Platillos
import com.example.recipe_app.PlatillosAdapter
import com.example.recipe_app.R
import com.example.recipe_app.db.DbHelper

class FragmentIngredientes  : Fragment(){
    private lateinit var ingredientesAdapter: IngredientesAdapter
    private lateinit var platillosAdapter: PlatillosAdapter
    private lateinit var recyclerViewPlatillos: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ingredientes, container, false)

        val recyclerViewIngredientes = view.findViewById<RecyclerView>(R.id.activity_recycler_ingredientes)
        recyclerViewIngredientes.layoutManager = GridLayoutManager(context, 2)
        val ingredientes = cargarIngredientes()
        ingredientesAdapter = IngredientesAdapter(ingredientes)
        recyclerViewIngredientes.adapter = ingredientesAdapter

        // Configuración del RecyclerView de platillos
        recyclerViewPlatillos = view.findViewById<RecyclerView>(R.id.activity_recycler_platillos)
        recyclerViewPlatillos.layoutManager = GridLayoutManager(context, 1)
        platillosAdapter = PlatillosAdapter(listOf())  // Inicialmente vacío
        recyclerViewPlatillos.adapter = platillosAdapter

        val btnBuscar = view.findViewById<Button>(R.id.btnBuscar)
        btnBuscar.setOnClickListener {
            val ingredientesSeleccionados = ingredientesAdapter.obtenerIngredientesSeleccionados()
            val platillosFiltrados = cargarPlatillosFiltrados(ingredientesSeleccionados)
            platillosAdapter = PlatillosAdapter(platillosFiltrados)
            recyclerViewPlatillos.adapter = platillosAdapter
        }

        return view
    }

    fun cargarIngredientes(): List<Ingredientes.Ingrediente> {
        val ingredientes = mutableListOf<Ingredientes.Ingrediente>()
        val dbHelper = DbHelper(context)
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT cat_in_ingrediente FROM cat_ingredientes", null)
        while (cursor.moveToNext()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("cat_in_ingrediente"))
            ingredientes.add(Ingredientes.Ingrediente(nombre))
        }
        cursor.close()
        db.close()

        return ingredientes
    }

    private fun cargarPlatillosFiltrados(ingredientes: List<String>): List<Platillos.Platillo> {
        val platillos = mutableListOf<Platillos.Platillo>()
        val dbHelper = DbHelper(requireActivity()) // Usando 'requireActivity()' para obtener el contexto
        val db = dbHelper.readableDatabase

        // Construir la consulta SQL con los ingredientes
        val sqlQuery = StringBuilder("SELECT cat_re_nombre, cat_re_descripcion FROM cat_recetas WHERE ")
        ingredientes.forEachIndexed { index, nombre ->
            if (index > 0) sqlQuery.append("AND ")
            sqlQuery.append("cat_re_ingredientes LIKE '%$nombre%' ")
        }

        val cursor = db.rawQuery(sqlQuery.toString(), null)
        while (cursor.moveToNext()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("cat_re_nombre"))
            val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("cat_re_descripcion"))
            platillos.add(Platillos.Platillo(nombre, descripcion))
        }
        cursor.close()
        db.close()

        return platillos
    }

}
