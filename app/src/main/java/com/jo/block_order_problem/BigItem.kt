package com.jo.block_order_problem

data class BigItem(
    val title: String,
    var smallItems: MutableList<SmallItem>
)