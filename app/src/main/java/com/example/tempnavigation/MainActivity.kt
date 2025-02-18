package com.example.tempnavigation

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.tempnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.decorView.setOnApplyWindowInsetsListener { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom - 120)
            insets
        }
            val insetsController = window.decorView.windowInsetsController
            insetsController?.hide(android.view.WindowInsets.Type.systemBars() or android.view.WindowInsets.Type.statusBars())
            insetsController?.systemBarsBehavior = android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController

        binding.bottomNav.setOnItemSelectedListener {
            if(it.itemId == R.id.firstFragmentItem){
                if(navController.currentDestination?.id != R.id.firstFragment){
                    navController.navigate(R.id.action_secondFragment_to_firstFragment)
                }
            }
            if(it.itemId == R.id.secondFragmentItem){
                if(navController.currentDestination?.id != R.id.secondFragment){
                    navController.navigate(R.id.action_firstFragment_to_secondFragment)
                }
            }
            true
        }

        binding.popupMenu.setOnClickListener {
            val popupmenu = PopupMenu(this,it,Gravity.END)
            popupmenu.inflate(R.menu.popup_menu)
            popupmenu.show()
//            popupmenu.menu.getItem(0).setOnMenuItemClickListener {
//                Toast.makeText(this,"You Selected Share..",Toast.LENGTH_SHORT).show()
//                true
//            }
//            popupmenu.menu.getItem(1).setOnMenuItemClickListener {
//                Toast.makeText(this,"You Selected Delete..",Toast.LENGTH_SHORT).show()
//                true
//            }
            popupmenu.setOnMenuItemClickListener { item->
                if(item.itemId == R.id.shareMenu){
                    Toast.makeText(this,"You Selected Share..",Toast.LENGTH_SHORT).show()
                }
                if(item.itemId == R.id.deleteMenu){
                    Toast.makeText(this,"You Selected Delete..",Toast.LENGTH_SHORT).show()
                }
                true
            }
        }

    }
}