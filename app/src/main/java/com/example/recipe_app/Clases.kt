package com.example.recipe_app

import android.app.Activity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.recipe_app.databinding.ActivityMainBinding

import com.example.recipe_app.Fragment.FragmentHome
import com.example.recipe_app.Fragment.FragmentIngredientes

import android.app.AlertDialog
import android.content.Context
import android.provider.Settings
import android.view.LayoutInflater
import android.os.Build
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.example.recipe_app.Fragment.FragmentRecetas
import com.google.android.material.navigation.NavigationView


import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.db.DbHelper


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Fragments
class MainScreenHandler(private val activity: AppCompatActivity, private val binding: ActivityMainBinding) {

    fun setupWith() {
        // Configura el adapter del ViewPager2 con los fragments.
        val fragmentsList = listOf(FragmentHome(), FragmentIngredientes(), FragmentRecetas())
        val adapter = ViewPagerAdapter(activity, fragmentsList)
        binding.viewPager.adapter = adapter

        // Mapeo de itemId a índice del ViewPager para simplificar el listener de BottomNavigationView.
        val menuToPageIndexMap = mapOf(
            R.id.navigation_home to 0,
            R.id.navigation_ingredientes to 1,
            R.id.navigation_recetas to 2
        )

        // Establece el listener para el BottomNavigationView para cambiar fragments.
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            binding.viewPager.currentItem = menuToPageIndexMap[item.itemId] ?: 0
            true
        }

        // Sincroniza el ViewPager con el BottomNavigationView.
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })
    }

    private inner class ViewPagerAdapter(activity: AppCompatActivity, private val fragmentsList: List<Fragment>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = fragmentsList.size
        override fun createFragment(position: Int): Fragment = fragmentsList[position]
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  Esta chucha debe controlar el btn de hamburgesa que despliega el
class MenuLateral(val activity: AppCompatActivity) { // esta es la clase que se llama desde el main para el btn hamburguesas
    private lateinit var drawerLayout: DrawerLayout

    fun setupDrawer() {
        drawerLayout = activity.findViewById(R.id.drawer_layout)
        val btnHamburguesa: ImageView = activity.findViewById(R.id.btnHamburguesa)
        btnHamburguesa.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}

class InfoExtra(private val context: Context, private val navigationView: NavigationView) {
    init {
    setupNavigationListener()
}

    private fun setupNavigationListener() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_about -> {
                    ShowInfoPhone()
                    true
                }
                R.id.nav_terms -> {
                    showTerminos()
                    true
                }
                    R.id.nav_dark_mode -> {
                    Darkmode()
                    true
                }
                else -> false
            }
        }
    }

    private fun Darkmode(){
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
    private fun showTerminos() {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.custom_alert, null)

        val alertTitle = dialogView.findViewById<TextView>(R.id.alertTitle)
        val alertMessage = dialogView.findViewById<TextView>(R.id.alertMessage)

        alertTitle.text = context.getString(R.string.terms_more)
        alertMessage.text = context.getString(R.string.Terminos)

        val alertDialog = AlertDialog.Builder(context).apply {
            setView(dialogView)
        }.create()

        val btnAccept = dialogView.findViewById<Button>(R.id.btnAcceptar)
        btnAccept.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
        alertDialog.setCancelable(false)
    }

    fun ShowInfoPhone() {
        // Obtén la información del dispositivo
        val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val fabricante = Build.MANUFACTURER
        val marca = Build.BRAND
        val modelo = Build.MODEL
        val version = Build.VERSION.SDK_INT.toString()

        val message = "ID: $deviceId\nFabricante: $fabricante\nMarca: $marca\nModelo: $modelo\nVersión: $version"

        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.custom_alert, null)

        val alertTitle = dialogView.findViewById<TextView>(R.id.alertTitle)
        alertTitle.text = "Información del Dispositivo"

        val alertMessage = dialogView.findViewById<TextView>(R.id.alertMessage)
        alertMessage.text = message

        val alertDialog = AlertDialog.Builder(context).apply {
            setView(dialogView)
        }.create()

        val btnAccept = dialogView.findViewById<Button>(R.id.btnAcceptar)
        btnAccept.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
        alertDialog.setCancelable(false)
    }
}

class SearchHandler(private val context: Context, private val searchEditText: EditText, private val resultsRecyclerView: RecyclerView) {
    private val dbHelper = DbHelper(context)
    private val resultsAdapter = ResultsAdapter()

    init {
        resultsRecyclerView.layoutManager = LinearLayoutManager(context)
        resultsRecyclerView.adapter = resultsAdapter
        setupSearch()
    }

    private fun updateResults(results: List<String>) {
        // Correr en el hilo principal
        (context as? Activity)?.runOnUiThread {
            if (results.isNotEmpty()) {
                resultsAdapter.updateData(results)
                resultsRecyclerView.visibility = View.VISIBLE
            } else {
                resultsRecyclerView.visibility = View.GONE
            }
        }
    }



    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                performSearch(s.toString())
            }
        })
    }

    private fun performSearch(query: String) {
        if (query.isNotEmpty()) {
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("SELECT cat_re_nombre FROM CAT_RECETAS WHERE cat_re_nombre LIKE '%$query%'", null)
            val results = mutableListOf<String>()
            while (cursor.moveToNext()) {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("cat_re_nombre"))
                results.add(nombre)
            }
            cursor.close()
            db.close()
            updateResults(results)
        } else {
            updateResults(emptyList())
        }
    }
}

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {
    private var data = listOf<String>()

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(text: String) {
            textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<String>) {
        data = newData
        notifyDataSetChanged()
    }
}
