package com.example.buttsweetsfinal

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.adapters.ProductsAdapter
import com.example.buttsweetsfinal.cart.ShoppingCart
import com.example.buttsweetsfinal.cart.ShoppingCartActivity
import com.example.buttsweetsfinal.network.BaseActivity
import com.example.buttsweetsfinal.viewmodel.ActivityProductViewModel
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_products.*
import org.koin.android.ext.android.inject

class ActivityProducts : BaseActivity() {
    private val viewModel: ActivityProductViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        setContentView(R.layout.activity_products)
//        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))
//        swipeRefreshLayout.isRefreshing = true

        val categoryId = intent.getStringExtra("categoryId")
//        swipeRefreshLayout.setOnRefreshListener {
//            viewModel.getProducts(categoryId)
//        }

        cart_size.text = ShoppingCart.getShoppingCartSize().toString()
        viewModel.getProducts(categoryId)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        basketButton.setOnClickListener {
            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }
    }

    private fun setObservers() {
        viewModel.observable.observe(this, {
            if (it.error == null) {
                products_recyclerview.adapter = it.value?.let { it1 -> ProductsAdapter(it1) }
            }
        })
    }

}
