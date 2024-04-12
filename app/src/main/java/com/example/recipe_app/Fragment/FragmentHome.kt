package com.example.recipe_app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.R
import java.util.Locale

class FragmentHome : Fragment() {
    private lateinit var categoryAdapter: CategoryAdapter

    private val categories = listOf(
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewCategories = view.findViewById<RecyclerView>(R.id.recycler_view_categories)
        recyclerViewCategories.layoutManager = GridLayoutManager(context, 3)
        categoryAdapter = CategoryAdapter(categories)
        categoryAdapter.onClickItem = { category ->
            actualizaPlatillos(category)
        }
        recyclerViewCategories.adapter = categoryAdapter
    }


    private fun actualizaPlatillos(category: Category) {
        val platillos:List<Platillo> = getPlatilloPorCategoria(category)

        val recyclerPlatillos = requireView().findViewById<RecyclerView>(R.id.recycler_view_categories)
        recyclerPlatillos.layoutManager = LinearLayoutManager(requireContext())
        val categoryAdapter = PlatillosAdapter(platillos)
        recyclerPlatillos.adapter = categoryAdapter
    }
}


class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var onClickItem: (Category) -> Unit = {}

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewIcon: ImageView = itemView.findViewById(R.id.image_view_category_icon)
        val textViewName: TextView = itemView.findViewById(R.id.text_view_category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recycler_categorias, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.textViewName.text = category.name
        holder.imageViewIcon.setImageResource(category.icon)
        holder.itemView.setOnClickListener {
            onClickItem.invoke(category)
        }
    }

    override fun getItemCount() = categories.size
}

class PlatillosAdapter(private val categories: List<Platillo>) :
    RecyclerView.Adapter<PlatillosAdapter.PlatilloViewHolder>() {

    var onClickItem: (Platillo) -> Unit = {}

    class PlatilloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val descr: TextView = itemView.findViewById(R.id.descrip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recycler_platillos, parent, false)
        return PlatilloViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val platillo = categories[position]
        holder.titulo.text = platillo.titulo
        holder.descr.text = platillo.descr
        holder.itemView.setOnClickListener {
            onClickItem.invoke(platillo)
        }
    }

    override fun getItemCount() = categories.size
}

data class Category(val name: String, val icon: Int)
data class Platillo(val titulo: String, val descr: String)