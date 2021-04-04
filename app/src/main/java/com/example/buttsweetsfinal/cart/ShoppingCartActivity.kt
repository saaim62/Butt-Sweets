package com.example.buttsweetsfinal

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.ShoppingCart.Companion.distroy
import com.example.buttsweetsfinal.ShoppingCart.Companion.getCart
import com.example.buttsweetsfinal.adapters.ShoppingCartAdapter
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_shopping_cart.*


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

        val name =
            getCart().fold(String()) { acc, cartItem -> acc + "\n" + cartItem.product.name + "   qty: " + cartItem.quantity + "  per piece price: " + cartItem.product.price + "\n" }

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
//            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
//            val myEdit = sharedPreferences.edit()
//            myEdit.putString("name", etName.text.toString())
//            myEdit.putString("address", etAddress.text.toString())
//            myEdit.putString("contact no", etPhoneNo.text.toString())
//            myEdit.apply()

//            val sh = getSharedPreferences("MySharedPref", MODE_APPEND)
//            val userName = sh.getString("name", "")
//            val userAddress = sh.getString("address", "")
//            val userContactNo = sh.getString("contact No", "")
//            val etName:TextView = findViewById(R.id.etName)

//            userName?.text= etName.toString()
            val dialogButtonClose: ImageView =
                dialog.findViewById<View>(R.id.dialCloseBtn) as ImageView
            dialogButtonClose.setOnClickListener { dialog.dismiss() }
            val dialogButtonConfirm: Button =
                dialog.findViewById<View>(R.id.dialConfirmBtn) as Button
            dialogButtonConfirm.setOnClickListener {
                Toast.makeText(
                    this,
                    "Your Order Has been placed",
                    Toast.LENGTH_SHORT
                ).show()
                val i = Intent(Intent.ACTION_SEND)
                i.type = "message/rfc822"
                i.putExtra(Intent.EXTRA_EMAIL, arrayOf("saaim62@gmail.com"))
                i.putExtra(Intent.EXTRA_SUBJECT, "User Order")
                i.putExtra(
                    Intent.EXTRA_TEXT,
                    "[\n USER DATA \n\n name: ${"userName"} \n address: ${"userAddress"} \n contact No: ${"userContactNo"} \n\n [\nproduct: ${name} \n\n\n total price: ${totalPrice}\n]"
                )
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."))
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this,
                        "There are no email clients installed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            dialog.show()
        }

        fabCartClearBtn.setOnClickListener {
            distroy()
            finish()
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }
    }


//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
//    fun showDialog(view: View?) {
//        val dialog = Dialog(this)
//        dialog.setTitle("Order Confirm")
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.dialogbrand_layout)
//        dialog.window?.setLayout(1400, 1800)
//        dialog.window?.setElevation(20F)
//        val dialogButtonClose: ImageView = dialog.findViewById<View>(R.id.dialCloseBtn) as ImageView
//        dialogButtonClose.setOnClickListener { dialog.dismiss() }
//        val dialogButtonConfirm: Button = dialog.findViewById<View>(R.id.dialConfirmBtn) as Button
//        dialogButtonConfirm.setOnClickListener {
//            Toast.makeText(
//                this,
//                "Your Order Has been placed",
//                Toast.LENGTH_SHORT
//            ).show()
//            val i = Intent(Intent.ACTION_SEND)
//            i.type = "message/rfc822"
//            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("saaim62@gmail.com"))
//            i.putExtra(Intent.EXTRA_SUBJECT, "User Order")
//            i.putExtra(Intent.EXTRA_TEXT, dataCart)
//            try {
//                startActivity(Intent.createChooser(i, "Send mail..."))
//            } catch (ex: ActivityNotFoundException) {
//                Toast.makeText(
//                    this,
//                    "There are no email clients installed.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//        dialog.show()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()

            }
        }

        return item.let { super.onOptionsItemSelected(it) }
    }
}
