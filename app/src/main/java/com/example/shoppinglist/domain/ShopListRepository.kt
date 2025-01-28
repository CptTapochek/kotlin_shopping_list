package com.example.shoppinglist.domain

interface ShopListRepository {

    fun addShoppItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shoppItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>

}