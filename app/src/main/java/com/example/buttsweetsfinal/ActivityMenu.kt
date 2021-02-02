package com.example.buttsweetsfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        bottomNav()

    }
    private fun bottomNav() {
        val bottomNavigationView: BottomNavigationView =
            findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        bottomNavigationView.menu.getItem(0).setIcon(R.drawable.homeicon)
        bottomNavigationView.menu.getItem(1).setIcon(R.drawable.lines)
        bottomNavigationView.menu.getItem(2).setIcon(R.drawable.cart)
        bottomNavigationView.menu.getItem(3).setIcon(R.drawable.profileicon)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomHome -> {
                    val intent = Intent(this, ActivityMain::class.java)
                    startActivity(intent)
                }
                R.id.bottomMenu -> {
                    val intent = Intent(this, ActivityMenu::class.java)
                    startActivity(intent)
                }
                R.id.bottomOrders -> {
                    val intent = Intent(this, ShoppingCartActivity::class.java)
                    startActivity(intent)
                }
                R.id.bottomProfile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
    }
}