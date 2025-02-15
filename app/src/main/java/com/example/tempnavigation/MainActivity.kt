package com.example.tempnavigation

import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
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
            val popupmenu = PopupMenu(this,it)
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