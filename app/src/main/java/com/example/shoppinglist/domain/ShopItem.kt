package com.example.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID,   // is var, because can to not add id, val is for the mandatory elements (-1 means we're not added id to the element, and app have to add that automatically)
) {
    companion object {
        const val UNDEFINED_ID = -1;
    }
}
