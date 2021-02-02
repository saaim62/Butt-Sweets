package com.example.buttsweetsfinal.adapters

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.ActivityProductList
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.ShoppingCart
import com.example.buttsweetsfinal.network.CartItem
import com.example.buttsweetsfinal.network.Product
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.product_row_item.view.*
import kotlinx.android.synthetic.main.product_row_item.view.product_image
import kotlinx.android.synthetic.main.product_row_item.view.product_name
import kotlinx.android.synthetic.main.product_row_item.view.product_price

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])


        (context as ActivityProductList).coordinator

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

                var qty = 0
                itemView.addToCart.setOnClickListener { view ->
                    qty++
                    itemView.tvValCounter.text = qty.toString()
                    val item = CartItem(product)

                    ShoppingCart.addItem(item)
                    //notify users
                    Snackbar.make(
                        (itemView.context as ActivityProductList).coordinator,
                        "${product.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()


                    it.onNext(ShoppingCart.getCart())


                }

//                itemView.removeItem.setOnClickListener { view ->
//
//                    val item = CartItem(product)
//
//                    ShoppingCart.removeItem(item, itemView.context)
//
//                    Snackbar.make(
//                        (itemView.context as ActivityProductList).coordinator,
//                        "${product.name} removed from your cart",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//
//                    it.onNext(ShoppingCart.getCart())
//
//                }


            }).subscribe { cart ->

                var quantity = 0

                cart.forEach { cartItem ->

                    quantity += cartItem.quantity
                }


                (itemView.context as ActivityProductList).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
            }


//            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {
//
//
//                 itemView.img_deleteitem.setOnClickListener { view ->
//
//                     val item = CartItem(product)
//
//                     ShoppingCart.removeItem(item,product_name.context)
//
//                     Snackbar.make(
//                         (itemView.context as ActivityProductList).coordinator,
//                         "${product.name} removed from your cart",
//                         Snackbar.LENGTH_LONG
//                     ).show()
//
//                     it.onNext(ShoppingCart.getCart())
//
//                 }
//
//
//             }).subscribe { cart ->
//
//                 var quantity = 0
//
//                 cart.forEach { cartItem ->
//
//                     quantity += cartItem.quantity
//                 }
//
//
//                 (itemView.context as ActivityProductList).cart_size.text = quantity.toString()
//                 Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
//
//
//             }
//
//
//                Toast.makeText(itemView.context, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()


        }
//        init {
//            var score = 0
//            itemView.tvBtnAdd.setOnClickListener {
//                score++
//                itemView.tvCounterVal.text = score.toString()
//                Toast.makeText(product_name.context, "Add", Toast.LENGTH_LONG).show()
//            }
//            itemView.tvBtnMin.setOnClickListener {
//                if (score <= 0) {
//                    Toast.makeText(product_name.context, "Qty Cannot be less then 0", Toast.LENGTH_LONG).show()
//                } else {
//                    score--
//                    itemView.tvCounterVal.text = score.toString()
//                    Toast.makeText(product_name.context, "Minus", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }

}