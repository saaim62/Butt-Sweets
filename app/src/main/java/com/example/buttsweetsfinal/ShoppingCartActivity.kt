package com.example.buttsweetsfinal

import android.app.Dialog
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.adapters.ShoppingCartAdapter
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter


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


        adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        shopping_cart_recyclerView.adapter = adapter

        shopping_cart_recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)


        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.price!!.toDouble()) }


        total_price.text = "Rs ${totalPrice}"

        checkOutBtn.setOnClickListener {
            showDialog(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun showDialog(view: View?) {
        val dialog = Dialog(this)
        dialog.setTitle("Order Confirm")
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogbrand_layout)
        dialog.window?.setLayout(1400, 1800)
        dialog.window?.setElevation(20F)
        val dialogButtonClose: ImageView = dialog.findViewById<View>(R.id.dialCloseBtn) as ImageView
        dialogButtonClose.setOnClickListener { dialog.dismiss() }
        val dialogButtonConfirm: Button = dialog.findViewById<View>(R.id.dialConfirmBtn) as Button
        dialogButtonConfirm.setOnClickListener {
            Toast.makeText(
                this,
                "Your Order Has been placed",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.show()
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
