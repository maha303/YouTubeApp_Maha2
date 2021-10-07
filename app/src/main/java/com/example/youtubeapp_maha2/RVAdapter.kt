package com.example.youtubeapp_maha2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter (private val items: Array<Array<String>>): RecyclerView.Adapter<RVAdapter.ItemViewHolder> () {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myBtnV : Button = itemView.cwBtn1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val title = items[position][0]
        val url = items[position][1]
        holder.myBtnV.text=title
        holder.myBtnV.setOnClickListener {
            player.loadVideo(url,0f)
            }
        }
    override fun getItemCount() = items.size

}
