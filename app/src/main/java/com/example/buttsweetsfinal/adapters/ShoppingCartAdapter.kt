package com.example.buttsweetsfinal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.network.CartItem

class ShoppingCartAdapter(
    private var cartItems: List<CartItem>,
    private var addItem: (CartItem) -> Unit,
    private var removeItem: (CartItem) -> Unit,
) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cartItem = cartItems[position]
        Glide.with(viewHolder.product_name.context)
            .load(cartItem.product.images[0].src)
            .into(viewHolder.product_image)
        viewHolder.product_name.text = cartItem.product.name
        viewHolder.product_price.text = cartItem.product.price
        viewHolder.product_quantity.text = cartItem.quantity.toString()

//        viewHolder.img_deleteitem.setOnClickListener {
//            ShoppingCart.delete(cartItem, context)
//            notifyDataSetChanged()
//        }

        viewHolder.cart_plus_img.setOnClickListener {
            addItem.invoke(cartItem)
        }

        viewHolder.cart_minus_img.setOnClickListener {
            removeItem.invoke(cartItem)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var product_name: TextView = itemView.findViewById(R.id.product_name)
        var product_price: TextView = itemView.findViewById(R.id.product_price)
        var product_quantity: TextView = itemView.findViewById(R.id.product_quantity)
        var product_image: ImageView = itemView.findViewById(R.id.product_image)

        //        var img_deleteitem: ImageView = itemView.findViewById(R.id.img_deleteitem)
        var cart_minus_img: ImageView = itemView.findViewById(R.id.cart_minus_img)
        var cart_plus_img: ImageView = itemView.findViewById(R.id.cart_plus_img)
    }

    fun updateList(list: List<CartItem>) {
        cartItems = list
    }
}