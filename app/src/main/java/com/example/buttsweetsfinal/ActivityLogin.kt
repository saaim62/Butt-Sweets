package com.example.buttsweetsfinal

import android.R.attr.name
import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_login.*


class ActivityLogin : AppCompatActivity() {
    private val TAG = "ActivityLogin"
    private val REQUEST_SIGNUP = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.inject(this)
        btn_login?.setOnClickListener { login() }
        link_signup!!.setOnClickListener {
            val intent = Intent(applicationContext, ActivitySignUp::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
        }
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(1400, 1800)
        btn_skip?.setOnClickListener {
            val intent = Intent(applicationContext, ActivityMain::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("WrongConstant")
    fun login() {
        Log.d(TAG, "Login")
//        if (!validate()) {
//            onLoginFailed()
//            return
//        }
        btn_login?.isEnabled = false
        val progressDialog = ProgressDialog(
            this@ActivityLogin
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()
        etEmail!!.text.toString()
        etPassword!!.text.toString()

        // TODO: Implement your own authentication logic here.
        Handler().postDelayed(
            { // On complete call either onLoginSuccess or onLoginFailed
                val sh = getSharedPreferences("MySharedPref", MODE_APPEND)
                val s1 = sh.getString("email", "")
                val a = sh.getString("password", "")
                tvLoginPass.text = a.toString()
                tvLoginEmail.text = s1.toString()

                if (etEmail == tvLoginPass.text && etPassword == tvLoginPass.text){
                    Toast.makeText(baseContext, "Login", Toast.LENGTH_LONG).show()
                    onLoginSuccess()
                }

//                onLoginFailed()
                progressDialog.dismiss()
            }, 3000
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                finish()
            }
        }
    }

    override fun onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true)
    }

    @SuppressLint("WrongConstant")
    fun onLoginSuccess() {
        Toast.makeText(baseContext, "Login", Toast.LENGTH_LONG).show()
        btn_login?.isEnabled = true
        val sh = getSharedPreferences("MySharedPref", MODE_APPEND)
        val s1 = sh.getString("email", "")
        val a = sh.getString("password", "")
//                age.setText(a.toString())
        if (etEmail == tvLoginPass.text && etPassword == tvLoginPass.text){
            bottomNavigationView.visibility = View.VISIBLE
            val intent = Intent(this, ActivityMain::class.java)
            startActivity(intent)
            finish()
        }
//        bottomNavigationView.visibility = View.VISIBLE
//        val intent = Intent(this, ActivityMain::class.java)
//        startActivity(intent)
//        finish()
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login", Toast.LENGTH_LONG).show()
        btn_login?.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true
        val email = etEmail!!.text.toString()
        val password = etPassword!!.text.toString()
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail!!.error = "enter a valid email address"
            valid = false
        } else {
            etEmail!!.error = null
        }
        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            etPassword!!.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            etPassword!!.error = null
        }
        return valid
    }
}