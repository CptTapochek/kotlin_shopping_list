package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0;

    init {
        // Blank data for test
       for (idx in 0 until 10) {
           val item = ShopItem("Item $idx", idx, true)
           addShoppItem(item)
       }
    }

    override fun addShoppItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
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

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()    //.toList() created a copy of list (its not safe to return the object)
    }
}