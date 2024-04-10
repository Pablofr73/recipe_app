package com.example.recipe_app.Fragment

class Recycler_Recetas (private val items: List<ItemReceta>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            return when (items[position]) {
                is Titulo -> VIEW_TYPE_TITULO
                is Ingrediente -> VIEW_TYPE_INGREDIENTE
                is PasoReceta -> VIEW_TYPE_PASO_RECETA
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                VIEW_TYPE_TITULO -> TituloViewHolder(inflater.inflate(R.layout.layout_titulo_platillo, parent, false))
                VIEW_TYPE_INGREDIENTE -> IngredienteViewHolder(inflater.inflate(R.layout.layout_ingredientes_platillo, parent, false))
                VIEW_TYPE_PASO_RECETA -> PasoRecetaViewHolder(inflater.inflate(R.layout.layout_receta_platillos, parent, false))
                else -> throw IllegalArgumentException("Invalid view type")
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is TituloViewHolder -> holder.bind(items[position] as Titulo)
                is IngredienteViewHolder -> holder.bind(items[position] as Ingrediente)
                is PasoRecetaViewHolder -> holder.bind(items[position] as PasoReceta)
            }
        }

        override fun getItemCount() = items.size
    }

}