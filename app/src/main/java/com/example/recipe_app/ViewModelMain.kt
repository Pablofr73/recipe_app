package com.example.recipe_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMain : ViewModel() {

    class Receta(
        var nombre: String = "",
        var ingredientes: String = "",
        var receta: String = ""
    )


    private val _rellenarReceta: MutableLiveData<Receta> = MutableLiveData(Receta())
    val rellenarReceta:LiveData<Receta> = _rellenarReceta
    fun setNuevaReceta(nuevaReceta:Receta) = _rellenarReceta.postValue(nuevaReceta)
}