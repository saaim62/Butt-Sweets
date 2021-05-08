package com.example.buttsweetsfinal.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buttsweetsfinal.ActivityProducts
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.cart.ShoppingCart
import com.example.buttsweetsfinal.cart.ShoppingCart.distroy
import com.example.buttsweetsfinal.cart.ShoppingCart.getEachShoppingCartSize
import com.example.buttsweetsfinal.network.CartItem
import com.example.buttsweetsfinal.network.Product
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.product_row_item.view.*
import okhttp3.internal.notifyAll


class ProductsAdapter(var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindProduct(products[position])
        (viewHolder.itemView.context as ActivityProducts).coordinator
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var product_name: TextView = itemView.findViewById(R.id.product_name)

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {
            itemView.product_size_spinner.visibility = View.VISIBLE
            itemView.tvSize.visibility = View.VISIBLE
            itemView.product_name.text = product.name
            itemView.product_price.text = product.price
//            product_size_spinner = product.attributes[0].options.toString()
            Glide.with(product_name.context)
                .load(product.images[0].src)
                .into(itemView.product_image)
            //                val products = mutableListOf<Product>()
//                products.add(product)
//                ShoppingCart.addItem(item)
            itemView.product_size_spinner.setItems("1 Pound", "2 Pound")
            itemView.product_size_spinner.setOnItemSelectedListener { view, position, id, item ->
                when (item) {
                    "1 Pound" -> {
                        var price: String? = null
                        val x: String
                        val value: Int = 2
                        x = product.id?.plus(value).toString()
                        Toast.makeText(itemView.context, x, Toast.LENGTH_SHORT).show()
//                        apiService.getPrice().enqueue(object : retrofit2.Callback<List<Product>> {
//                            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                                price = response.body().toString()
//                            }
//
//                            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
//                                print(t.message)
//                                t.message?.let { Log.d("Data error", it) }
//                                Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()
//                            }
//                        })
                    }
                    "2 Pound" -> {
                        var price: String? = null
                        val x: String
                        val value: Int = 3
                        x = product.id?.plus(value).toString()
                        Toast.makeText(itemView.context, x, Toast.LENGTH_SHORT).show()
//                        apiService.getPrice().enqueue(object : retrofit2.Callback<List<Product>> {
//                            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                                price = response.body().toString()
//                            }
//
//                            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
//                                print(t.message)
//                                t.message?.let { Log.d("Data error", it) }
//                                Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()
//                            }
//                        })
                    }
                }
                Snackbar.make(
                    view,
                    "Clicked $item",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {


                itemView.addToCart.setOnClickListener { view ->
                    val item = CartItem(product)
                    ShoppingCart.addItem(item)
                    //notfy users
                    Snackbar.make(
                        (itemView.context as ActivityProducts).coordinator,
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
                        (itemView.context as ActivityProducts).coordinator,
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
                (itemView.context as ActivityProducts).cart_size.text = quantity.toString()
            }
        }
    }
//    fun areAllItemsEnabled(): Boolean {
//        return true
//    }
//
//    fun isEnabled(position: Int): Boolean {
//        return true
//    }
//
//    fun registerDataSetObserver(observer: DataSetObserver?) {}
//
//    fun unregisterDataSetObserver(observer: DataSetObserver?) {}
//
//    fun getCount(): Int {
//        return data.size
//    }
//
//    fun getItem(position: Int): String? {
//        return data.get(position)
//    }
//
//    fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        var convertView = convertView
//        val holder: SpinnerViewHolder
//        if (convertView == null) {
//            convertView = TextView(mContext)
//            holder = SpinnerViewHolder()
//            holder.textView = convertView
//            convertView.setTag(holder)
//        } else {
//            holder = convertView.tag as SpinnerViewHolder
//        }
//        holder.textView!!.text = getItem(position)
//        return convertView
//    }
//
//    fun getViewTypeCount(): Int {
//        return 0
//    }
//
//    fun isEmpty(): Boolean {
//        return false
//    }
//
//    class SpinnerViewHolder {
//        var textView: TextView? = null
//    }

}