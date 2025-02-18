package com.jo.block_order_problem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv: RecyclerView = findViewById(R.id.rv)
        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        val adapter = RecyclerViewAdapter()
        val bigItem1 = Item.BigItem("대주제1")
        val bigItem2 = Item.BigItem("대주제2")
        val bigItem3 = Item.BigItem("대주제3")
        adapter.itemList = mutableListOf(
            bigItem1,
            Item.SmallItem("소주제1", bigItem1),
            Item.SmallItem("소주제2", bigItem1),
            Item.SmallItem("소주제3", bigItem1),
            bigItem2,
            Item.SmallItem("소주제1", bigItem2),
            Item.SmallItem("소주제2", bigItem2),
            Item.SmallItem("소주제3", bigItem2),
            Item.SmallItem("소주제4", bigItem2),
            bigItem3,
            Item.SmallItem("소주제1", bigItem3),
            Item.SmallItem("소주제2", bigItem3),
        )
        rv.adapter = adapter
        adapter.notifyDataSetChanged()

        val helper = ReItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlgs = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags,0)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        })
        helper.attachToRecyclerView(rv)
    }
}