package com.example.buttsweetsfinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageView
import java.util.*


class SplashActivity : Activity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val sPlashScreen: SplashActivity = this
        setContentView(R.layout.activity_splash)
        val imageView = findViewById<ImageView>(R.id.ivSplashLogo)
        imageView.scaleType = ImageView.ScaleType.FIT_XY

        //not going to use this feature in near future.
        val ids = intArrayOf(R.drawable.rlogo, R.drawable.rlogo)
        val randomGenerator = Random()
        val r = randomGenerator.nextInt(ids.size)
        imageView.setImageDrawable(resources.getDrawable(ids[r]))
        imageView.animate().rotation(360f).duration = 4000
        val anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        imageView.clearAnimation()
        imageView.startAnimation(anim)
        val textView = findViewById<ImageView>(R.id.tvSmartMaintenance)
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim2.reset()
        textView.clearAnimation()
        textView.startAnimation(anim2)

//        intent.setClass(sPlashScreen, ActivityMain::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        //                Toast.makeText(SplashActivity.this, "Firing engine: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
        val SPLASH_TIME_OUT = 4500
        val mCountDownTimer: CountDownTimer =
            object : CountDownTimer(SPLASH_TIME_OUT.toLong(), 6000) {
                override fun onTick(millisUntilFinished: Long) {
//                Toast.makeText(SplashActivity.this, "Firing engine: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
                }

                override fun onFinish() {
                    val intent = Intent(this@SplashActivity, ActivityMain::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }
            }
        mCountDownTimer.start()
    }
}