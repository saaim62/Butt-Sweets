package com.example.buttsweetsfinal.user

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.buttsweetsfinal.ActivityMain
import com.example.buttsweetsfinal.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_fire_base_login.*
import kotlinx.android.synthetic.main.activity_fire_base_login.btn_login
import kotlinx.android.synthetic.main.activity_fire_base_login.btn_skip_login
import kotlinx.android.synthetic.main.activity_fire_base_login.etEmail
import kotlinx.android.synthetic.main.activity_fire_base_login.etPassword
import kotlinx.android.synthetic.main.activity_fire_base_login.link_signup


class FireBaseLoginActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(1400, 1800)

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()
//        if (auth!!.currentUser != null) {
//            startActivity(Intent(this@FireBaseLoginActivity, ActivityMain::class.java))
//            finish()
//        }

        // set the view now
        setContentView(R.layout.activity_fire_base_login)
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()
        link_signup.setOnClickListener {
            finish()
            startActivity(
                Intent(
                    this@FireBaseLoginActivity,
                    FireBaseRegisterActivity::class.java
                )
            )
        }
//        btnReset.setOnClickListener(object : View.OnClickListener {
//            fun onClick(v: View?) {
//                startActivity(Intent(this@fireBaseLoginActivity, ResetPasswordActivity::class.java))
//            }
//        })

        btn_skip_login.setOnClickListener {
            finish()
        }
        btn_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val email = etEmail!!.text.toString()
                val password = etPassword!!.text.toString()
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(
                        applicationContext,
                        "Enter email address!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                //authenticate user
                auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this@FireBaseLoginActivity
                    ) { task ->
                        if (!task.isSuccessful) {
                            // there was an error
                            if (password.length < 6) {
                                etPassword!!.error = getString(R.string.minimum_password)
                            } else {
                                Toast.makeText(
                                    this@FireBaseLoginActivity,
                                    getString(R.string.auth_failed),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
//                            val intent = Intent(this@FireBaseLoginActivity, ActivityMain::class.java)
//                            startActivity(intent)
                            finish()
                        }
                    }
            }
        })
    }
}