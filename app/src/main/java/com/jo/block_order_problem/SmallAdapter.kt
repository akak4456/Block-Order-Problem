package com.jo.block_order_problem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SmallAdapter : RecyclerView.Adapter<SmallAdapter.SmallHolder>() {

    var itemList: MutableList<SmallItem> = mutableListOf()

    inner class SmallHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(item: SmallItem) {
            if(item.isShow) {
                rootView.findViewById<View>(R.id.root_layout).visibility = View.VISIBLE
            } else {
                rootView.findViewById<View>(R.id.root_layout).visibility = View.GONE
            }
            rootView.findViewById<TextView>(R.id.tv_small).text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallHolder {
        return SmallHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_small, parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SmallHolder, position: Int) {
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