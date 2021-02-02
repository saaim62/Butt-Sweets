package com.example.buttsweetsfinal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.network.CartItem
import kotlinx.android.synthetic.main.cart_list_item.view.*

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
            Glide.with(product_name.context)
                    .load(cartItem.product.images[0].src)
                    .into(itemView.product_image)
            itemView.product_name.text = cartItem.product.name
            itemView.product_price.text = cartItem.product.price
            itemView.product_quantity.text = cartItem.quantity.toString()
        }
    }
}