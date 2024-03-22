package com.example.recipe_app

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.recipe_app.databinding.ActivityMainBinding

import com.example.recipe_app.Fragment.FragmentHome
import com.example.recipe_app.Fragment.FragmentRecetas
import com.example.recipe_app.Fragment.FragmentIngredientes


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Fragments
class MainScreenHandler(private val activity: AppCompatActivity, private val binding: ActivityMainBinding) {

    fun setupWith() {
        // Configura el adapter del ViewPager2 con los fragments.
        val fragmentsList = listOf(FragmentHome(), FragmentRecetas(), FragmentIngredientes())
        val adapter = ViewPagerAdapter(activity, fragmentsList)
        binding.viewPager.adapter = adapter

        // Mapeo de itemId a índice del ViewPager para simplificar el listener de BottomNavigationView.
        val menuToPageIndexMap = mapOf(
            R.id.navigation_home to 0,
            R.id.navigation_recetas to 1,
            R.id.navigation_ingredientes to 2
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



