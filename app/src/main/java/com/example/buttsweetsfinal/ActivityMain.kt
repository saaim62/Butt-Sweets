package com.example.buttsweetsfinal

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.GridView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.buttsweetsfinal.cart.ShoppingCartActivity
import com.example.buttsweetsfinal.user.FireBaseLoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.gridhome.*


class ActivityMain : AppCompatActivity() {
    //    private lateinit var adapter: GridAdapter
//    private var gd: GridData = GridData()
    private lateinit var gv: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        onClickListeners()
        video()
        bottomNav()
        cardBackground()

        val intent = Intent(this, FireBaseLoginActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        video()
    }

    override fun onDestroy() {
        super.onDestroy()
//        distroy()
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
                    R.id.bottomHome
                }
//                R.id.bottomMenu -> {
//                    val intent = Intent(this, ActivityMenu::class.java)
//                    startActivity(intent)
//                }
                R.id.bottomOrders -> {
                    val intent = Intent(this, ShoppingCartActivity::class.java)
                    startActivity(intent)
                }
                R.id.bottomProfile -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
    }

    private fun video() {
        val videoView = findViewById<View>(R.id.videoView) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.introvid)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.start()
    }

    private fun cardBackground() {
        cardList.setBackgroundResource(R.drawable.shape_react01)
        card2.setBackgroundResource(R.drawable.shape_react01)
        card3.setBackgroundResource(R.drawable.shape_react01)
        card4.setBackgroundResource(R.drawable.shape_react01)
        card5.setBackgroundResource(R.drawable.shape_react01)
        card6.setBackgroundResource(R.drawable.shape_react01)
    }

    private fun moveToProductsScreen(categoryId: String) {
        Handler().postDelayed({
            val intent = Intent(
                this@ActivityMain,
                ActivityProducts::class.java
            )
            intent.putExtra("categoryId", categoryId)
            startActivity(intent)
            overridePendingTransition(
                R.anim.avatar_slide_in_from_left,
                R.anim.avatar_slide_out_to_left
            )
        }, 10)
    }

    private fun onClickListeners() {
//        searchView.setOnClickListener {
//            etSearch.visibility = View.VISIBLE
//            searchBtnClose.visibility = View.VISIBLE
//        }
//        searchBtnClose.setOnClickListener {
//            etSearch.visibility = View.GONE
//            searchBtnClose.visibility = View.GONE
//        }
        cardImgCake.setOnClickListener {
            moveToProductsScreen("58")
        }

        cardImgSamosa.setOnClickListener {
            moveToProductsScreen("57")
        }

        cardImgSweet.setOnClickListener {
            moveToProductsScreen("62")
        }

        cardImgHalwa.setOnClickListener {
            moveToProductsScreen("90")
        }

        cardImgInstant.setOnClickListener {
            moveToProductsScreen("56")
        }

        cardImgTvc.setOnClickListener {
            moveToProductsScreen("57")
        }
    }
}