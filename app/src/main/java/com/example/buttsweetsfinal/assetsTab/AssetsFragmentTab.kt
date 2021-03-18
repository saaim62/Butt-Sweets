package com.example.buttsweetsfinal.assetsTab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.buttsweetsfinal.ProfileActivity
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.network.Product
import kotlinx.android.synthetic.main.fragment_assets_tab.*
import java.util.*

class AssetsFragmentTab : Fragment() {
    var mContext: Context? = null
    var totalList: List<Product>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assets_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val receivedList = bundle?.getSerializable(CHILD_LIST) as List<Product>

        tabTopBack.setOnClickListener {
            activity?.onBackPressed()
        }

        tvAssetTabCountVal?.text = receivedList.size.toString()

        imageViewAssetTabProfile.setOnClickListener {
            activity?.startActivity(Intent(context, ProfileActivity::class.java))
        }

        rvAssets.adapter = AssetsAdapterTab(R.layout.assets_row, receivedList, totalList!!,
            { asset ->
//                    assetDetailTab(asset)
            }
        ) { asset ->
            val childAssets: MutableList<Product> = ArrayList()
//            for (oneAsset in totalList!!) {
//                if (oneAsset.parent_id != null) {
//                    if (oneAsset.parent_id.equals(asset.id, ignoreCase = true)) {
//                        childAssets.add(oneAsset)
//                    }
//                }
//            }
            if (childAssets.size > 0) {
                (view.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.flFragmentContainer,
                        newInstance(totalList!!)
                    ).addToBackStack("tag").commit()
            } else {
            }
        }
    }

//    private fun assetDetailTab(asset: Product) {
//        val i = Intent(view?.context, AssetTabDetailActivity::class.java)
//        data = asset
//        view?.context?.startActivity(i)
//    }

    companion object {
        var childName: List<String>? = null
        var data: Product? = null
        const val TAG = "AssetsActivity"
        private const val QR_SCAN_REQUEST_CODE = 534
        const val TOTAL_LIST = "total_list"
        const val CHILD_LIST = "child_list"
        fun newInstance(totalList: List<Product>): Fragment {
            val fragment = AssetsFragmentTab()
            val args = Bundle()
            args.putSerializable(TOTAL_LIST, totalList as ArrayList<Product?>?)
            fragment.arguments = args
            return fragment
        }
    }
}