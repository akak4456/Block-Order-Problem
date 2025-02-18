package com.jo.block_order_problem

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv: RecyclerView = findViewById(R.id.rv)
        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        val adapter = BigAdapter()
        adapter.itemList = mutableListOf(
            BigItem(
                "대주제1",
                mutableListOf(
                    SmallItem("소주제1-1"),
                    SmallItem("소주제1-2"),
                    SmallItem("소주제1-3"),
                )
            ),
            BigItem(
                "대주제2",
                mutableListOf(
                    SmallItem("소주제2-1"),
                    SmallItem("소주제2-2"),
                    SmallItem("소주제2-3"),
                    SmallItem("소주제2-4")
                )
            ),
            BigItem(
                "대주제3",
                mutableListOf(
                    SmallItem("소주제3-1"),
                    SmallItem("소주제3-2"),
                )
            ),
            BigItem(
                "대주제4",
                mutableListOf(
                    SmallItem("소주제4-1"),
                    SmallItem("소주제4-2"),
                    SmallItem("소주제4-3"),
                    SmallItem("소주제4-4"),
                    SmallItem("소주제4-5"),
                    SmallItem("소주제4-6"),
                    SmallItem("소주제4-7"),
                    SmallItem("소주제4-8"),
                    SmallItem("소주제4-9"),
                    SmallItem("소주제4-10"),
                )
            ),
            BigItem(
                "대주제5",
                mutableListOf(
                    SmallItem("소주제5-1"),
                    SmallItem("소주제5-2"),
                    SmallItem("소주제5-3"),
                    SmallItem("소주제5-4"),
                    SmallItem("소주제5-5"),
                )
            )
        )
        rv.adapter = adapter
        rv.addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                // 부모에서 자식 RecyclerView에 터치 이벤트가 가는 것을 막는 로직을 추가할 수 있습니다.
                return false // false를 반환하면 자식 RecyclerView가 터치 이벤트를 처리할 수 있도록 허용
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
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