package com.jo.block_order_problem

sealed class Item {
    data class BigItem(
        val text: String
    ): Item()

    data class SmallItem(
        val text: String,
        val relatedBigItem: BigItem,
        var isShow: Boolean = true,
    ): Item()
}