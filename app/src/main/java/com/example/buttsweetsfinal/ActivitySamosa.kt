package com.example.buttsweetsfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.buttsweetsfinal.adapters.CakeAdapter
import com.example.buttsweetsfinal.adapters.SamosaAdapter
import com.example.buttsweetsfinal.network.APIConfig
import com.example.buttsweetsfinal.network.APIService
import com.example.buttsweetsfinal.network.Product
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_samosa.*
import kotlinx.android.synthetic.main.activity_samosa.products_recyclerview
import kotlinx.android.synthetic.main.activity_samosa.showCart
import kotlinx.android.synthetic.main.activity_samosa.swipeRefreshLayout

class ActivitySamosa: AppCompatActivity()  {
    private lateinit var apiService: APIService
    private lateinit var samosaAdapter: SamosaAdapter

    private var products = listOf<Product>()
//    val APIConfig = APIConfig.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)

        setContentView(R.layout.activity_samosa)


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

        cart_size_samosa.text = ShoppingCart.getShoppingCartSize().toString()

        getProducts()


        showCart.setOnClickListener {

            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }

    }


    fun getProducts() {

        apiService.getSamosa().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                print(t.message)
                t.message?.let { Log.d("Data error", it) }
                Toast.makeText(this@ActivitySamosa, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                swipeRefreshLayout.isRefreshing = false
//                swipeRefreshLayout.isEnabled = false

                products = response.body()!!

                samosaAdapter = SamosaAdapter(this@ActivitySamosa, products)

                products_recyclerview.adapter = samosaAdapter

//                samosaAdapter.notifyDataSetChanged()

            }

        })
    }
}