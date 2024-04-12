import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.Fragment.Ingredientes
import com.example.recipe_app.R

class IngredientesAdapter(private val ingredientes: List<Ingredientes.Ingrediente>) : RecyclerView.Adapter<IngredientesAdapter.ViewHolder>() {

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
        // Configura aquí el CheckBox si es necesario
    }

    override fun getItemCount() = ingredientes.size
}
