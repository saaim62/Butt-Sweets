package com.example.buttsweetsfinal

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_sign_up.*


class ActivitySignUp : AppCompatActivity() {
    private val TAG = "SignupActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
//        ButterKnife.inject(this)
        btn_signup?.setOnClickListener { signup() }
        link_login?.setOnClickListener {
            finish()
        }
        btn_skip?.setOnClickListener {
            finish()
        }
    }

    fun signup() {
        Log.d(TAG, "Signup")
        if (!validate()) {
            onSignupFailed()
            return
        }
        btn_signup?.isEnabled = false
        val progressDialog = ProgressDialog(
            this@ActivitySignUp
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        // TODO: Implement your own signup logic here.
        Handler().postDelayed(
            { // On complete call either onSignupSuccess or onSignupFailed
                // depending on success
                onSignupSuccess()
                // onSignupFailed();
                progressDialog.dismiss()
            }, 3000
        )
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("email", etEmail.text.toString())
        myEdit.putString("password", etPassword.text.toString())
        myEdit.apply()
    }


    fun onSignupSuccess() {
        btn_signup?.isEnabled = true
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("email", etEmail.text.toString())
        myEdit.putString("password", etPassword.text.toString())
        myEdit.apply()
        Toast.makeText(baseContext, "SignUP successful", Toast.LENGTH_LONG).show()
        setResult(RESULT_OK, null)
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(baseContext, "SignUp failed", Toast.LENGTH_LONG).show()
        btn_signup?.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true
        val name = etName!!.text.toString()
        val email = etEmail!!.text.toString()
        val password = etPassword!!.text.toString()
        if (name.isEmpty() || name.length < 3) {
            etName!!.error = "at least 3 characters"
            valid = false
        } else {
            etName!!.error = null
        }
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
