package com.jo.block_order_problem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var itemList: MutableList<Item> = mutableListOf()
    inner class RecyclerViewHolder(val rootView: View): RecyclerView.ViewHolder(rootView) {
        fun bind(item: Item) {
            when(item) {
                is Item.BigItem -> {
                    rootView.findViewById<TextView>(R.id.tv_big).text = item.text
                }
                is Item.SmallItem -> {
                    rootView.findViewById<TextView>(R.id.tv_small).text = item.text
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = if(viewType == BIG_ITEM_TYPE) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_big, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_small, parent, false)
        }
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int  = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]) {
            is Item.BigItem -> BIG_ITEM_TYPE
            is Item.SmallItem -> SMALL_ITEM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    companion object {
        const val BIG_ITEM_TYPE = 0
        const val SMALL_ITEM_TYPE = 1
    }
}