package com.rsstest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsstest.databinding.ItemHolderBinding
import com.rsstest.ui.MainViewModelImpl.Item

class MainHolder(
    private val binding: ItemHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(entity: Item) {
        binding.item.text = entity.result
    }
}
