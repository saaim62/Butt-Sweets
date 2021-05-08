package com.example.buttsweetsfinal.cart

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.adapters.ShoppingCartAdapter
import com.example.buttsweetsfinal.cart.ShoppingCart.distroy
import com.example.buttsweetsfinal.cart.ShoppingCart.getCart
import com.example.buttsweetsfinal.mailSender.SendMail
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import okhttp3.internal.notifyAll


class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setSupportActionBar(toolbar)
        Paper.init(this)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary),
            PorterDuff.Mode.SRC_ATOP
        )
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        adapter = ShoppingCartAdapter(this, getCart())
        adapter.notifyDataSetChanged()
        shopping_cart_recyclerView.adapter = adapter
        shopping_cart_recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)

        val totalPrice = getCart().fold(0.toDouble()) { acc, cartItem ->
            acc + cartItem.quantity.times(
                cartItem.product.price!!.toDouble()
            )
        }

        val orderDetails =
            getCart().fold(String()) { acc, cartItem -> acc + "\n\n" + cartItem.product.name + "   qty: " + cartItem.quantity + "  per piece price: " + cartItem.product.price + "\n" }

        total_price.text = "Rs ${totalPrice}"
        checkOutBtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setTitle("Order Confirm")
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.activity_order_dialog)
//            dialog.window?.setLayout(1400, 1800)
//            dialog.window?.setElevation(20F)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.show()
            dialog.window?.attributes = lp
            val dialogButtonClose: ImageView =
                dialog.findViewById<View>(R.id.dialCloseBtn) as ImageView
            dialogButtonClose.setOnClickListener { dialog.dismiss() }
            val dialogButtonConfirm: Button =
                dialog.findViewById<View>(R.id.dialConfirmBtn) as Button
            if (totalPrice < 800) {
                dialogButtonConfirm.isEnabled = false
            } else {
                dialogButtonConfirm.setOnClickListener {
                    val etName: EditText =
                        dialog.findViewById<View>(R.id.etName) as EditText
                    val userName = etName.text!!.toString()

                    val etAddress: EditText =
                        dialog.findViewById<View>(R.id.etAddress) as EditText
                    val userAddress = etAddress.text!!.toString()

                    val etPhoneNo: EditText =
                        dialog.findViewById<View>(R.id.etPhoneNo) as EditText
                    val userPhoneNo = etPhoneNo.text!!.toString()

                    val etDemands: EditText =
                        dialog.findViewById<View>(R.id.etDemands) as EditText
                    val userDemands = etDemands.text.toString()

                    val etEmail: EditText =
                        dialog.findViewById<View>(R.id.etEmail) as EditText
                    val email = etEmail.text!!.toString()

                    if(!email.isNullOrEmpty() || !userName.isNullOrEmpty() || !userAddress.isNullOrEmpty() || !userPhoneNo.isNullOrEmpty()){
                        Toast.makeText(
                            this,
                            "Your Order Has been placed",
                            Toast.LENGTH_SHORT
                        ).show()
                        val subject = "User Orders"
                        val message =
                            "User Data \n [\n Email Address: $email \n name: $userName \n address: $userAddress \n contact No: $userPhoneNo \n] \n\n Order Details \n [\n Special Demands: $userDemands \n $orderDetails \n\n\n total price: ${totalPrice}\n]"
                        val sm = SendMail(this, email, subject, message)
                        sm.execute()
                    }
                    else{
                        Toast.makeText(
                            this,
                            "please fill the above fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
            dialog.show()
        }

        fabCartClearBtn.setOnClickListener {
            distroy()
            adapter.notifyDataSetChanged()
//            finish()
//            val intent = Intent(this, ShoppingCartActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return item.let { super.onOptionsItemSelected(it) }
    }
}
