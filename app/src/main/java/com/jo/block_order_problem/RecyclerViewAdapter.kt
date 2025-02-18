package com.jo.block_order_problem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var itemList: MutableList<Item> = mutableListOf()

    inner class RecyclerViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: Item) {
            when (item) {
                is Item.BigItem -> {
                    rootView.findViewById<TextView>(R.id.tv_big).text = item.text
                    rootView.findViewById<Button>(R.id.btn).setOnClickListener {
                        var isExpanded = true
                        var startPos = -1
                        var endPos = -1
                        itemList.forEachIndexed { idx, itm ->
                            if (itm is Item.SmallItem && itm.relatedBigItem == item) {
                                if (startPos == -1) {
                                    startPos = idx
                                }
                                if (itm.isShow) {
                                    isExpanded = false
                                    itm.isShow = false
                                } else {
                                    isExpanded = true
                                    itm.isShow = true
                                }
                            }
                            if (startPos != -1 && endPos == -1) {
                                if (idx == itemList.size - 1) {
                                    endPos = idx
                                } else if (itm is Item.BigItem) {
                                    endPos = idx - 1
                                }
                            }
                        }
                        println("$startPos $endPos")
                        notifyItemRangeChanged(startPos, endPos - startPos + 1)
                        if (isExpanded) {
                            rootView.findViewById<Button>(R.id.btn).text = "접기"
                        } else {
                            rootView.findViewById<Button>(R.id.btn).text = "펼치기"
                        }
                    }
                }

                is Item.SmallItem -> {
                    if (item.isShow) {
                        rootView.findViewById<View>(R.id.root_layout).visibility = View.VISIBLE
                    } else {
                        rootView.findViewById<View>(R.id.root_layout).visibility = View.GONE
                    }
                    rootView.findViewById<TextView>(R.id.tv_small).text = item.text
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = if (viewType == BIG_ITEM_TYPE) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_big, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_small, parent, false)
        }
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
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