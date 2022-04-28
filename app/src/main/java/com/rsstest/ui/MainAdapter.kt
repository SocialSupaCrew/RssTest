package com.rsstest.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rsstest.core.BaseAdapter
import com.rsstest.ui.MainViewModelImpl.Item


class MainAdapter : BaseAdapter<Item, MainHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(parent)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}
