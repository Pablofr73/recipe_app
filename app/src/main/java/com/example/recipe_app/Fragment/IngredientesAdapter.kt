import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.Fragment.Ingredientes
import com.example.recipe_app.R

class IngredientesAdapter(private val ingredientes: List<Ingredientes.Ingrediente>) : RecyclerView.Adapter<IngredientesAdapter.ViewHolder>() {

    private val seleccionados = MutableList(ingredientes.size) { false }  // Todos los ingredientes empiezan no seleccionados

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_ingredientes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingrediente = ingredientes[position]
        holder.textView.text = ingrediente.nombre
        holder.checkBox.isChecked = seleccionados[position]

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            seleccionados[position] = isChecked
        }
    }

    override fun getItemCount() = ingredientes.size
    fun obtenerIngredientesSeleccionados(): List<String> {
        return ingredientes.filterIndexed { index, _ -> seleccionados[index] }.map { it.nombre }
    }
}

