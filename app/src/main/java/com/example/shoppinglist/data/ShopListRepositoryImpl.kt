package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})    //Sorting list
    private var autoIncrementId = 0;

    init {
        // Blank data for test
       for (idx in 0 until 10000) {
           val item = ShopItem("Item $idx", idx, true)
           addShoppItem(item)
       }
    }

    override fun addShoppItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShoppItem(shopItem)
    }

    override fun getShopItem(shoppItemId: Int): ShopItem {
        return shopList.find {
            it.id == shoppItemId
        } ?: throw RuntimeException("Element with id $shoppItemId nor found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData    //.toList() created a copy of list (its not safe to return the object)
    }

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
    }
}