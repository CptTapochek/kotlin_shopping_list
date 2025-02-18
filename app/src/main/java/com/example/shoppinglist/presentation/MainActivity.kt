package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shoppListAdapter: ShopListAdaptor
//    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
//        llShopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            /* optimized */
            shoppListAdapter.submitList(it)

            /* non-optimized */
            /*showList(it)*/
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shoppListAdapter = ShopListAdaptor()
            adapter = shoppListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_ENABLED, ShopListAdaptor.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_DISABLED, ShopListAdaptor.MAX_POOL_SIZE)
        }
        onShopItemLongClickListener()
        onShopItemClickListener()
        onShopItemDissmisibleDelete(rvShopList)
    }

    private fun onShopItemDissmisibleDelete(rvShopList: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shoppListAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun onShopItemClickListener() {
        shoppListAdapter.onShopItemClickListener = {
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun onShopItemLongClickListener() {
        shoppListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
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
