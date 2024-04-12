package com.example.recipe_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PlatillosAdapter(private val platillos: List<Platillos.Platillo>) : RecyclerView.Adapter<PlatillosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.titulo)
        val descripcionTextView: TextView = view.findViewById(R.id.descrip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_platillos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val platillo = platillos[position]
        holder.nombreTextView.text = platillo.nombre
        holder.descripcionTextView.text = platillo.descripcion
    }

    override fun getItemCount() = platillos.size
}
