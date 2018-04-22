package com.example.recyclerviewdiffutilexample

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class ItemArrayAdapter(private val listItemClickListener: ListItemClickListener)
    : ListAdapter<ListItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    class ListItemCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.text == newItem.text && oldItem.clicks == newItem.clicks
        }
    }

    interface ListItemClickListener {
        fun onItemClick(listItem : ListItem, position : Int)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ListenItemViewHolder).bind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListenItemViewHolder(view)
    }

    inner class ListenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView = itemView.tv_content
        var tvClickCount: TextView = itemView.tv_click_counts

        fun bind(listItem : ListItem, position : Int) {
            tvContent.text = listItem.text

            if (listItem.clicks > 0) {
                tvClickCount.text = "" + listItem.clicks
            } else {
                tvClickCount.text = ""
            }

            itemView.setOnClickListener {
                listItemClickListener.onItemClick(listItem, position)
            }
            Log.d("onclick", "onClick " + position + " " + tvContent.text)
        }
    }
}
