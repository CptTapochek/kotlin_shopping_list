package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdaptor
//    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
//        llShopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            /* optimized */
            adapter.shopList = it

            /* non-optimized */
            /*showList(it)*/
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            adapter = ShopListAdaptor()
            adapter = adapter
            recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_ENABLED, ShopListAdaptor.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_DISABLED, ShopListAdaptor.MAX_POOL_SIZE)
        }
    }

//    private fun showList(list: List<ShopItem>) {
//        llShopList.removeAllViews()
//        for (shopItem in list) {
//            val layoutId = if (shopItem.enabled) {
//                R.layout.item_shop_enabled
//            } else {
//                R.layout.item_shop_disabled
//            }
//            val view = LayoutInflater.from(this).inflate(layoutId, llShopList, false)
//
//            val tvName = view.findViewById<TextView>(R.id.tv_name)
//            val tvCount = view.findViewById<TextView>(R.id.tv_count)
//            tvName.text = shopItem.name
//            tvCount.text = shopItem.count.toString()
//
//            view.setOnLongClickListener {
//                viewModel.changeEnableState(shopItem)
//                true
//            }
//
//            llShopList.addView(view)
//        }
//    }
}
