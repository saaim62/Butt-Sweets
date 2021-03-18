package com.example.buttsweetsfinal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.ActivityInstantBake
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.ShoppingCart
import com.example.buttsweetsfinal.ShoppingCart.Companion.distroy
import com.example.buttsweetsfinal.ShoppingCart.Companion.getEachShoppingCartSize
import com.example.buttsweetsfinal.network.CartItem
import com.example.buttsweetsfinal.network.Product
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_instantbake.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class InstantBakeAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<InstantBakeAdapter.ViewHolder>() {
    private var prevPos = 0
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        setAnimation(viewHolder.cvItem, position)
        viewHolder.bindProduct(products[position])
        (context as ActivityInstantBake).coordinator
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var product_name: TextView = itemView.findViewById(R.id.product_name)
        var cvItem: ConstraintLayout = itemView.findViewById(R.id.cvItem)

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {
            itemView.product_name.text = product.name
            itemView.tvId.text = product.id.toString()
            itemView.product_price.text = product.price

//            Picasso.get().load(product.images[0].src).fit().into(itemView.product_image)
            Glide.with(product_name.context)
                .load(product.images[0].src)
                .into(itemView.product_image)

//            val products = mutableListOf<Product>()
//                products.add(product)
//                ShoppingCart.addItem(item)
            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {
                itemView.addToCart.setOnClickListener { view ->
                    val item = CartItem(product)
                    ShoppingCart.addItem(item)
                    //notfy users
                    Snackbar.make(
                        (itemView.context as ActivityInstantBake).coordinator,
                        "${product.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(ShoppingCart.getCart())
                    itemView.tvValCounter.text = getEachShoppingCartSize().toString()
                }
                itemView.removeItem.setOnClickListener { view ->
                    val item = CartItem(product)
                    ShoppingCart.removeItem(item, itemView.context)
                    Snackbar.make(
                        (itemView.context as ActivityInstantBake).coordinator,
                        "${product.name} removed from your cart",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(ShoppingCart.getCart())
                    itemView.tvValCounter.text = getEachShoppingCartSize().toString()
                }
            }).subscribe { cart ->
                var quantity = 0
                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                    if (quantity == 0) {
                        distroy()
                    }
                }
                (itemView.context as ActivityInstantBake).cart_size_instant.text =
                    quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = products.size

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > prevPos) {
            val animation = AnimationUtils.loadAnimation(
                viewToAnimate.context,
                android.R.anim.slide_in_left
            )
            viewToAnimate.startAnimation(animation)
            prevPos = position
        }
    }
    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        var cvItem: CardView = v.findViewById(R.id.cvItem)
    }
}