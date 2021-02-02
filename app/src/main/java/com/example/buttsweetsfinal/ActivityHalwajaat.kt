package com.example.buttsweetsfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.example.buttsweetsfinal.network.Product
import com.example.buttsweetsfinal.network.APIConfig
import com.example.buttsweetsfinal.network.APIService
import com.example.buttsweetsfinal.adapters.CakeAdapter
import com.example.buttsweetsfinal.adapters.HalwajaatAdapter
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_halwajaat.*
import retrofit2.Call
import retrofit2.Response

class ActivityHalwajaat : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var halwajaatAdapter: HalwajaatAdapter

    private var products = listOf<Product>()
//    val APIConfig = APIConfig.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)

        setContentView(R.layout.activity_halwajaat)


//        setSupportActionBar(toolbar)
        apiService = APIConfig.getRetrofitClient(this).create(APIService::class.java)


        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        swipeRefreshLayout.isRefreshing = true

        swipeRefreshLayout.setOnRefreshListener {
            getProducts()
        }

//        val layoutManager = StaggeredGridLayoutManager(this, Lin)

        products_recyclerview.layoutManager =
            androidx.recyclerview.widget.StaggeredGridLayoutManager(
                1,
                androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
            )


        cart_size_halwajaat.text = ShoppingCart.getShoppingCartSize().toString()

        getProducts()


        showCart.setOnClickListener {

            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }

    }


    fun getProducts() {

        apiService.getHalwajaat().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                print(t.message)
                t.message?.let { Log.d("Data error", it) }
                Toast.makeText(this@ActivityHalwajaat, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                swipeRefreshLayout.isRefreshing = false
//                swipeRefreshLayout.isEnabled = false

                products = response.body()!!

                halwajaatAdapter = HalwajaatAdapter(this@ActivityHalwajaat, products)

                products_recyclerview.adapter = halwajaatAdapter

//                halwajaatAdapter.notifyDataSetChanged()

            }

        })
    }

}
