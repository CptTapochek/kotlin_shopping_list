package com.example.shoppinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(shoppItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shoppItemId)
    }
}