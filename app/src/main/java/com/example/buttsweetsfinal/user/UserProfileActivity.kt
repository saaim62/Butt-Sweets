package com.example.buttsweetsfinal.user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.mailSender.SendMail
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        complainBtn.setOnClickListener {
            val userName = etName.text!!.toString()
            val userPhoneNo = etPhoneNo.text!!.toString()
            val userComplaints = etComplaints.text.toString()
            val email = etEmail.text!!.toString()
            val subject = "User Complaints"
            val message =
                "User Details \n [\n Email Address: $email \n name: $userName \n contact No: $userPhoneNo \n] \n User Complaint \n [\n Complaint: $userComplaints \n]"
            val sm = SendMail(this, email, subject, message)
            sm.execute()
            Toast.makeText(
                this,
                "Your complaint has been received",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

