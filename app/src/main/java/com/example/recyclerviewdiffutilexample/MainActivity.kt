package com.example.recyclerviewdiffutilexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), ItemArrayAdapter.ListItemClickListener {
    private val itemList = ArrayList<ListItem>()
    private lateinit var itemArrayAdapter : ItemArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemArrayAdapter = ItemArrayAdapter(this)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.adapter = itemArrayAdapter
        (0..99).mapTo(itemList) { ListItem("Item " + it) }
        itemArrayAdapter.submitList(itemList)

        btn_refresh.setOnClickListener {
            val newItemList = ArrayList<ListItem>()
            (0..Random().nextInt(100)).mapTo(newItemList) { ListItem("Item ${randomStr()} $it") }
            itemArrayAdapter.submitList(newItemList)
        }
    }

    override fun onItemClick(listItem: ListItem, position: Int) {
        itemList[position].clicks++

        val newItemList = ArrayList<ListItem>()
        itemList.forEach {
            newItemList.add(ListItem(it.text, it.clicks))
        }
        itemArrayAdapter.submitList(newItemList)
    }

    private fun randomStr() : String {
        val r = Random()
        return java.lang.Long.toString(r.nextLong() and java.lang.Long.MAX_VALUE, 36)
    }
}
