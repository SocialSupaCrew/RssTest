package com.rsstest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsstest.databinding.ActivityMainBinding
import com.rsstest.ui.MainViewModel.State
import com.rsstest.utils.isNotNullOrBlank
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getState().observe(this, ::applyState)

        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.itemList.adapter = adapter

        binding.btnConfirm.setOnClickListener {
            viewModel.computeListItem(
                binding.etLimit.text?.toString()?.toInt()!!,
                binding.etInt1.text?.toString()?.toInt()!!,
                binding.etInt2.text?.toString()?.toInt()!!,
                binding.etString1.text?.toString()!!,
                binding.etString2.text?.toString()!!,
            )
        }

        binding.etLimit.doAfterTextChanged { afterTextChanged() }
        binding.etInt1.doAfterTextChanged { afterTextChanged() }
        binding.etInt2.doAfterTextChanged { afterTextChanged() }
        binding.etString1.doAfterTextChanged { afterTextChanged() }
        binding.etString2.doAfterTextChanged { afterTextChanged() }
    }

    private fun applyState(state: State) = when (state) {
        is State.ItemListLoaded -> adapter.items = state.items
        State.ShowError -> {
            // TODO:
        }
    }

    private fun afterTextChanged() {
        binding.btnConfirm.isEnabled =
            binding.etLimit.text.isNotNullOrBlank()
                    && binding.etInt1.text.isNotNullOrBlank()
                    && binding.etInt2.text.isNotNullOrBlank()
                    && binding.etString1.text.isNotNullOrBlank()
                    && binding.etString2.text.isNotNullOrBlank()
    }
}
