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
import com.example.buttsweetsfinal.ShoppingCart.Companion.distroy
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.gridhome.*
import okhttp3.*


class ActivityMain : AppCompatActivity() {
    //    private lateinit var adapter: GridAdapter
//    private var gd: GridData = GridData()
    private lateinit var gv: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        onClickListeners()
        video()
        bottomNav()
        cardBackground()
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

    private fun onClickListeners() {
        searchView.setOnClickListener {
            etSearch.visibility = View.VISIBLE
            searchBtnClose.visibility = View.VISIBLE
        }
        searchBtnClose.setOnClickListener {
            etSearch.visibility = View.GONE
            searchBtnClose.visibility = View.GONE
        }
        cardImgCake.setOnClickListener {
            Handler().postDelayed({
                val mainIntent = Intent(
                    this@ActivityMain,
                    ActivityCake::class.java
                )
                mainIntent.putExtra("id", "1")
                startActivity(mainIntent)
                overridePendingTransition(
                    R.anim.avatar_slide_in_from_left,
                    R.anim.avatar_slide_out_to_left
                )
            }, 10)
//            startActivity(
//                Intent(
//                    this@ActivityMain,
//                    ActivityCake::class.java
//                )
//            )
        }
//        cardImgCake.setOnClickListener {
//            val fm = supportFragmentManager
//            fm.beginTransaction().replace(R.id.container, CakesFragment()).commit()
//        }

        cardImgSamosa.setOnClickListener {
//            startActivity(
//                Intent(
//                    this@ActivityMain,
//                    ActivitySamosa::class.java
//                )
//            )
            Handler().postDelayed({
                val mainIntent = Intent(
                    this@ActivityMain,
                    ActivitySamosa::class.java
                )
                mainIntent.putExtra("id", "1")
                startActivity(mainIntent)
                overridePendingTransition(
                    R.anim.avatar_slide_in_from_left,
                    R.anim.avatar_slide_out_to_left
                )
            }, 10)
        }

        cardImgSweet.setOnClickListener {
//            startActivity(
//                Intent(
//                    this@ActivityMain,
//                    ActivitySweets::class.java
//                )
//            )
            Handler().postDelayed({
                val mainIntent = Intent(
                    this@ActivityMain,
                    ActivitySweets::class.java
                )
                mainIntent.putExtra("id", "1")
                startActivity(mainIntent)
                overridePendingTransition(
                    R.anim.avatar_slide_in_from_left,
                    R.anim.avatar_slide_out_to_left
                )
            }, 10)
        }

        cardImgHalwa.setOnClickListener {
//            startActivity(
//                Intent(
//                    this@ActivityMain,
//                    ActivityHalwajaat::class.java
//                )
//            )
            Handler().postDelayed({
                val mainIntent = Intent(
                    this@ActivityMain,
                    ActivityHalwajaat::class.java
                )
                mainIntent.putExtra("id", "1")
                startActivity(mainIntent)
                overridePendingTransition(
                    R.anim.avatar_slide_in_from_left,
                    R.anim.avatar_slide_out_to_left
                )
            }, 10)
        }

        cardImgInstant.setOnClickListener {
//            startActivity(
//                Intent(
//                    this@ActivityMain,
//                    ActivityInstantBake::class.java
//                )
//            )
            Handler().postDelayed({
                val mainIntent = Intent(
                    this@ActivityMain,
                    ActivityInstantBake::class.java
                )
                mainIntent.putExtra("id", "1")
                startActivity(mainIntent)
                overridePendingTransition(
                    R.anim.avatar_slide_in_from_left,
                    R.anim.avatar_slide_out_to_left
                )
            }, 10)
        }

        cardImgTvc.setOnClickListener {
            startActivity(
                Intent(
                    this@ActivityMain,
                    ActivityTvc::class.java
                )
            )
        }
    }
}