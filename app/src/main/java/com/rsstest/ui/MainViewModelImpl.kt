package com.rsstest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsstest.core.EventPath
import com.rsstest.data.interactor.GetItemListInteractor
import kotlinx.coroutines.launch

class MainViewModelImpl(
    private val state: MediatorLiveData<State>,
    private val navigation: MutableLiveData<EventPath<Path>>,
    private val interactor: GetItemListInteractor
) : MainViewModel() {

    init {
        state.addSource(interactor.getLiveData(), ::onListResult)
    }

    override fun getState(): LiveData<State> = state

    override fun getNavigation(): LiveData<EventPath<Path>> = navigation

    override fun navigateTo(path: Path) {
        navigation.value = path.toEventPath()
    }

    override fun computeListItem(
        limit: Int,
        int1: Int,
        int2: Int,
        string1: String,
        string2: String
    ) {
        viewModelScope.launch { interactor.execute(limit, int1, int2, string1, string2) }
    }

    private fun onListResult(result: GetItemListInteractor.Result) {
        state.value = when (result) {
            is GetItemListInteractor.Result.OnSuccess -> State.ItemListLoaded(result.items)
            GetItemListInteractor.Result.OnError -> State.ShowError
        }
    }

    data class Item(val id: Int, val result: String)
}
