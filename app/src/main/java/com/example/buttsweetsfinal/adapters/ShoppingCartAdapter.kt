package com.example.buttsweetsfinal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.ActivityProductList
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.ShoppingCart
import com.example.buttsweetsfinal.network.CartItem
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.cart_list_item.view.product_image
import kotlinx.android.synthetic.main.cart_list_item.view.product_name
import kotlinx.android.synthetic.main.cart_list_item.view.product_price
import kotlinx.android.synthetic.main.product_row_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindItem(cartItems[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var product_name: TextView = itemView.findViewById(R.id.product_name)
        @SuppressLint("CheckResult")
        fun bindItem(cartItem: CartItem) {


//            Picasso.get().load(cartItem.product.images[0].src).fit().into(itemView.product_image)

            Glide.with(product_name.context)
                .load(cartItem.product.images[0].src)
                .into(itemView.product_image)
            itemView.product_name.text = cartItem.product.name

            itemView.product_price.text = cartItem.product.price

//            itemView.product_quantity.text = cartItem.quantity.toString()

//            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {
//
//
//                itemView.img_deleteitem.setOnClickListener { view ->
//
//                    val item = CartItem(cartItem.product)
//
//                    ShoppingCart.removeItem(item,product_name.context)
//
//                    Snackbar.make(
//                        (itemView.context as ActivityProductList).coordinator,
//                        "${cartItem.product.name} removed from your cart",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//
//                    it.onNext(ShoppingCart.getCart())
//
//                }
//
//
//            }).subscribe { cart ->
//
//                var quantity = 0
//
//                cart.forEach { cartItem ->
//
//                    quantity += cartItem.quantity
//                }
//
//
//                (itemView.context as ActivityProductList).cart_size.text = quantity.toString()
//                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
//
//
//            }
//
//
//            Toast.makeText(itemView.context, "${cartItem.product.name} added to your cart", Toast.LENGTH_SHORT).show()
        }

//        init {
//            var score = 0
//            itemView.img_deleteitem.setOnClickListener {
//                score++
//                itemView.tvCounterVal.text = score.toString()
//                Toast.makeText(product_name.context, "Add", Toast.LENGTH_LONG).show()
//            }
//            itemView.cart_minus_img.setOnClickListener {
//                score++
//                itemView.tvCounterVal.text = score.toString()
//                Toast.makeText(product_name.context, "Add", Toast.LENGTH_LONG).show()
//            }
//            itemView.cart_plus_img.setOnClickListener {
//                if (score <= 0) {
//                    Toast.makeText(product_name.context, "Qty Cannot be less then 0", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }

}