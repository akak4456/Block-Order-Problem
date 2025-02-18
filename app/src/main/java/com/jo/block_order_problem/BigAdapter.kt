package com.jo.block_order_problem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BigAdapter : RecyclerView.Adapter<BigAdapter.BigHolder>() {

    var itemList: MutableList<BigItem> = mutableListOf()

    inner class BigHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: BigItem) {
            val rv = rootView.findViewById<RecyclerView>(R.id.rv_big)
            val adapter = SmallAdapter()
            adapter.itemList = item.smallItems
            adapter.notifyDataSetChanged()
            rootView.findViewById<TextView>(R.id.tv_big).text = item.title
            rootView.findViewById<Button>(R.id.btn).setOnClickListener {
                item.smallItems.forEach { smallItem ->
                    smallItem.isShow = !smallItem.isShow
                }
                adapter.notifyDataSetChanged()
                if(item.smallItems.all { smallItem -> smallItem.isShow }) {
                    rootView.findViewById<Button>(R.id.btn).text = "접기"
                } else {
                    rootView.findViewById<Button>(R.id.btn).text = "펼치기"
                }
            }
            rv.layoutManager = LinearLayoutManager(rootView.context)
            rv.adapter = adapter

            val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigHolder {
        return BigHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_big, parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BigHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun onItemMove(from: Int, to: Int) : Boolean {
        val data = itemList[from]
        //리스트 갱신
        itemList.removeAt(from)
        itemList.add(to,data)

        // from에서 to 위치로 아이템 위치 변경
        notifyItemMoved(from,to)
        return true
    }

}