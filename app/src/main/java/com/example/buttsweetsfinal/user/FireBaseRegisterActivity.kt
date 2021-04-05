package com.example.buttsweetsfinal.user

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.buttsweetsfinal.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_fire_base_login.*
import kotlinx.android.synthetic.main.activity_fire_base_register.*
import kotlinx.android.synthetic.main.activity_fire_base_register.btn_skip
import kotlinx.android.synthetic.main.activity_fire_base_register.etEmail
import kotlinx.android.synthetic.main.activity_fire_base_register.etPassword


class FireBaseRegisterActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base_register)

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()

        btn_skip?.setOnClickListener {
            finish()
        }

        link_login.setOnClickListener {
            finish()
            startActivity(
                Intent(
                    this@FireBaseRegisterActivity,
                    FireBaseLoginActivity::class.java
                )
            )
        }

        btn_signup!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val email = etEmail!!.text.toString().trim { it <= ' ' }
                val password = etPassword!!.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                    return
                }
                if (password.length < 6) {
                    Toast.makeText(
                        applicationContext,
                        "Password too short, enter minimum 6 characters!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                //create user
                auth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this@FireBaseRegisterActivity
                    ) { task ->
                        Toast.makeText(
                            this@FireBaseRegisterActivity,
                            "createUserWithEmail:onComplete:" + task.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                        if (!task.isSuccessful) {
                            Toast.makeText(
                                this@FireBaseRegisterActivity, "Authentication failed." + task.exception,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
//                            startActivity(Intent(this@FireBaseRegisterActivity, ActivityMain::class.java))
                            finish()
                        }
                    }
            }
        })
    }

    override fun onResume() {
        super.onResume() }
}