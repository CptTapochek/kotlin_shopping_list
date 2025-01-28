package com.example.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShoppItem(shopItem: ShopItem) {
        shopListRepository.addShoppItem(shopItem)
    }
}