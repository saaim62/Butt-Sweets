package com.example.buttsweetsfinal.assetsTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.network.Product


class AssetsAdapterTab(
    private val rowLayout: Int,
    private val assetsToDisplay: List<Product>?,
    private val totalAssets: List<Product>,
    private val onClick: (Product) -> Unit,
    private val onClickChild: (Product) -> Unit
) : RecyclerView.Adapter<AssetsAdapterTab.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val asset = getItem(position)

        if (asset != null) {
            if (asset.name != null) {
                holder.tvName.text = asset.name
            }

            holder.tvTabChildAssetBtn.setOnClickListener { view: View ->
                holder.tvAssetTabRelationImg?.visibility = View.VISIBLE
                holder.tvAssetTabRelation?.visibility = View.VISIBLE
                onClickChild(asset)

            }
            holder.cl.setOnClickListener { view: View ->
                onClick(asset)
            }
        }
    }

    private fun getItem(position: Int): Product? {
        return assetsToDisplay?.get(position)
    }

    override fun getItemCount(): Int {
        return assetsToDisplay!!.size
    }

    inner class Holder(v: View) : RecyclerView.ViewHolder(v) {
        var cl: ConstraintLayout = v.findViewById(R.id.cl)
        var tvName: TextView = v.findViewById(R.id.name)
        var tvAssetTabRelationImg: ImageView? = v.findViewById(R.id.tvAssetTabRelationImg)
        var tvAssetTabRelation: TextView? = v.findViewById(R.id.tvAssetTabRelation)
        var tvTabChildAssetBtn: Button = v.findViewById(R.id.tvTabChildAssetBtn)
        var ivForwardArrow: ImageView = v.findViewById(R.id.ivForwardArrow)
    }
}