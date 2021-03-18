package com.example.buttsweetsfinal.assetsTab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.adapters.SamosaAdapter
import com.example.buttsweetsfinal.network.APIService
import com.example.buttsweetsfinal.network.Product
import kotlinx.android.synthetic.main.activity_samosa.*
import retrofit2.Call
import retrofit2.Response


class AssetsMainFragmentTab : Fragment() {
    private lateinit var apiService: APIService
    private lateinit var samosaAdapter: SamosaAdapter
    private var totalAssets = listOf<Product>()
    var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_assets_tab_container, container, false)
//        fetchAssetsFromServer()
        return v
    }

    private fun fetchAssetsFromServer() {
        apiService.getSamosa().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                print(t.message)
                t.message?.let { Log.d("Data error", it) }
                Toast.makeText(mContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                swipeRefreshLayout.isRefreshing = false
//                swipeRefreshLayout.isEnabled = false
                totalAssets = response.body()!!
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.flFragmentContainer,
                    AssetsFragmentTab.newInstance(totalAssets)
                )
                transaction?.commit()
            }
        })
    }
}