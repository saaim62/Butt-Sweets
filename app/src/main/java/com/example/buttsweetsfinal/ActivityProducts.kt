package com.example.buttsweetsfinal

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.adapters.ProductsAdapter
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.network.*
import com.example.buttsweetsfinal.providers.AlertDialogProvider
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.product_row_item.*

class ActivityProducts : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        setContentView(R.layout.activity_products)
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))
        swipeRefreshLayout.isRefreshing = true

        val categoryId = intent.getStringExtra("categoryId")
        swipeRefreshLayout.setOnRefreshListener {
            getProducts(categoryId)
        }

        cart_size.text = ShoppingCart.getShoppingCartSize().toString()
        getProducts(categoryId)
        basketButton.setOnClickListener {
            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }
    }

    private fun getProducts(categoryId: String?) {
        categoryId?.let { ApiManager.getProducts(this, this, categoryId = it) }
    }

    override fun onApiSuccess(apiResponse: BaseApiResponse) {
        super.onApiSuccess(apiResponse)
        if (apiResponse.collection.isNotEmpty()) {
            when (apiResponse.collection[0]) {
                is Product -> {
                    val products = apiResponse.collection as List<Product>
                    swipeRefreshLayout.isRefreshing = false
                    products_recyclerview.adapter = ProductsAdapter(this@ActivityProducts, products)
                }
            }
        }
    }

    override fun onApiFailure(errorCode: Int) {
        if (errorCode == HttpErrorCodes.Unauthorized.code) {
            AlertDialogProvider.showAlertDialog(
                this,
                DialogTheme.ThemeWhite,
                getString(R.string.response_fail)
            )
        } else {
            super.onApiFailure(errorCode)
        }
    }
}
