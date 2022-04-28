package com.rsstest.ui

import android.os.Bundle
import com.rsstest.core.BaseActivity
import com.rsstest.databinding.ActivityMainBinding
import com.rsstest.ui.MainViewModel.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getState().observe(this, ::applyState)
    }

    private fun applyState(state: State) = when (state) {
        is State.ItemListLoaded -> {
            // TODO:
        }
        State.ShowError -> {
            // TODO:
        }
    }
}
