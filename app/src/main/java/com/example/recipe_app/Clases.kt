package com.example.recipe_app

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView

class Clases {
    fun Drawable.setTintForMode(context: Context) {
        val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val colorRes = if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            R.color.white
        } else {
            R.color.black
        }
        DrawableCompat.setTint(this, ContextCompat.getColor(context, colorRes))
    }

}
class CategoryAdapter(var categories: List<MainActivity.Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.image_view_category_icon)
        val nameTextView: TextView = view.findViewById(R.id.text_view_category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_categorias, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.nameTextView.text = category.name
        holder.iconImageView.setImageResource(category.icon)
    }

    override fun getItemCount(): Int = categories.size
}
