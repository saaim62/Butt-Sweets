//package com.example.buttsweetsfinal.assetsTab
//
//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import android.view.MenuItem
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import com.example.buttsweetsfinal.R
//import com.example.buttsweetsfinal.network.Product
//
//import java.util.*
//
//class AssetTabDetailActivity : AppCompatActivity() {
//    private var asset: Product? = null
//    internal var mContext: Context = this
//    var totalList: List<Product>? = null
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_asset_tab_detail)
//        supportActionBar?.hide()
//
//        tvAssetName?.text = AssetsFragmentTab.childName?.let { java.lang.String.join(" / ", it) }
//        imageView_backArrow.setOnClickListener { onBackPressed() }
//        val i = intent
//        asset = AssetsFragmentTab.data
//        asset?.let { asset ->
//            tvName?.text = asset.name
//            if (asset.request_templates?.size != 0) {
//
//                if (asset.request_templates?.get(0)?.employee != null) {
//                    tvTabEmployeeVal.text = asset.request_templates?.get(0)?.employee
//                } else {
//                    tvTabEmployeeVal.text = "null"
//                }
//
//                if (asset.request_templates?.get(0)?.teams != null) {
//                    tvTabTeamVal.text = asset.request_templates?.get(0)?.teams
//                } else {
//                    tvTabTeamVal.text = "null"
//                }
//
//                if (asset.request_templates?.get(0)?.description != null) {
//                    tvTabDescriptionVal.text = asset.request_templates?.get(0)?.description
//                } else {
//                    tvTabDescriptionVal.text = "null"
//                }
//
//            } else {
////                Toast.makeText(this, "req temp null", Toast.LENGTH_SHORT).show()
//            }
//
//            if (asset.id != null) {
//                tvTabAssetUidVal.text = asset.id
//            } else {
//                tvTabAssetUidVal.text = "null"
//            }
//
//
//            if (asset.created_at != null) {
//                if (!asset.created_at?.equals("0", ignoreCase = true)!!) {
//                    val dueDateFormatted =
//                            MyDateTimeStamp.getDateTimeFormattedStringFromMilliseconds(
//                                    java.lang.Long.valueOf(asset.created_at!!)
//                            )
//                    tvTabDueCreatedVal.text = dueDateFormatted
//                    if (Calendar.getInstance().time.after(
//                                    MyDateTimeStamp.dateTimeStringToDate(
//                                            dueDateFormatted
//                                    )
//                            )
//                    ) {
//                        tvTabDueCreatedVal.setTextColor(
//                                ContextCompat.getColor(
//                                        this,
//                                        R.color.md_black
//                                )
//                        )
//                    }
//                } else {
//                    tvTabDueCreatedVal.text = "-"
//                }
//            } else {
//                tvTabDueCreatedVal.text = "-"
//            }
//
//            //                if (asset.machine_hours != null) {
////                    tvTabReadingsTime.text = asset.machine_hours.toString()
////                } else {
////                    tvTabReadingsTime.text = "null"
////                }
//
////                if (asset.pieces != null) {
////                    tvTabReadingsMachinePiecesVal.text = asset.pieces.toString()
////                } else {
////                    tvTabReadingsMachinePiecesVal.text = "null"
////                }
//        }
////        tvTabDetailReadingBtn.setOnClickListener {
////            startActivity(Intent(this, AssetDetailTabReadingActivity::class.java))
////        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            onBackPressed()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    companion object {
//        const val SELECTED_ASSET = "selected_asset"
//        var assetChildName = "child_asset_name"
//    }
//}