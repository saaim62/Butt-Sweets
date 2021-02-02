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
import com.example.buttsweetsfinal.ActivityHalwajaat
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.ShoppingCart
import com.example.buttsweetsfinal.ShoppingCart.Companion.distroy
import com.example.buttsweetsfinal.ShoppingCart.Companion.getEachShoppingCartSize
import com.example.buttsweetsfinal.network.CartItem
import com.example.buttsweetsfinal.network.Product
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_halwajaat.*
import kotlinx.android.synthetic.main.activity_samosa.coordinator
import kotlinx.android.synthetic.main.product_row_item.view.*

class HalwajaatAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
        RecyclerView.Adapter<HalwajaatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])
        (context as ActivityHalwajaat).coordinator
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var product_name: TextView = itemView.findViewById(R.id.product_name)

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {


            itemView.product_name.text = product.name
            itemView.product_price.text = product.price

//            Picasso.get().load(product.images[0].src).fit().into(itemView.product_image)
            Glide.with(product_name.context)
                    .load(product.images[0].src)
                    .into(itemView.product_image)

            //                val products = mutableListOf<Product>()
//                products.add(product)
//

//                ShoppingCart.addItem(item)


            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {


                itemView.addToCart.setOnClickListener { view ->
                    val item = CartItem(product)
                    ShoppingCart.addItem(item)
                    //notfy users
                    Snackbar.make(
                            (itemView.context as ActivityHalwajaat).coordinator,
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
                            (itemView.context as ActivityHalwajaat).coordinator,
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


                (itemView.context as ActivityHalwajaat).cart_size_halwajaat.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()


            }

        }
    }

}