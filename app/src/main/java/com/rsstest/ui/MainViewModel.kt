package com.rsstest.ui

import com.rsstest.core.BaseViewModel
import com.rsstest.core.EventPath
import com.rsstest.ui.MainViewModel.Path
import com.rsstest.ui.MainViewModel.State
import com.rsstest.ui.MainViewModelImpl.Item

abstract class MainViewModel : BaseViewModel<State, Path>() {
    sealed class State {
        data class ItemListLoaded(val items: List<Item>) : State()
        object ShowError : State()
    }

    sealed class Path : EventPath.Path {

    }

    abstract fun computeListItem(limit: Int, int1: Int, int2: Int, string1: String, string2: String)
}
