package com.example.recipe_app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipe_app.R
import com.example.recipe_app.ViewModelMain

class FragmentRecetas2 : Fragment() {
    private val mainViewModel: ViewModelMain by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recetas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.rellenarReceta.observe(viewLifecycleOwner) { receta ->
            if(receta.nombre.isNotBlank()) {
                requireView().findViewById<TextView>(R.id.tvTituloPlatillo)?.text = receta.nombre
                requireView().findViewById<TextView>(R.id.tvPasoReceta)?.text = receta.receta
            }
        }
    }

}

