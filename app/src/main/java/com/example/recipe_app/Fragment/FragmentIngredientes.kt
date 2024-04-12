package com.example.recipe_app.Fragment

import IngredientesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.R
import com.example.recipe_app.db.DbHelper

class FragmentIngredientes  : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredientes, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.activity_recycler_ingredientes)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Ajusta según necesites
        val ingredientes = cargarIngredientes() // Asume que esta función carga tus ingredientes desde SQLite
        recyclerView.adapter = IngredientesAdapter(ingredientes)
        return view
    }

    fun cargarIngredientes(): List<Ingredientes.Ingrediente> {
        val ingredientes = mutableListOf<Ingredientes.Ingrediente>()
        // Asegurándose de obtener el contexto de forma segura
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



}
