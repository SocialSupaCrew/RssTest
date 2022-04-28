package com.rsstest.data.interactor

import com.rsstest.core.domain.BaseInteractor
import com.rsstest.core.domain.Interactor
import com.rsstest.data.interactor.GetItemListInteractor.Result
import com.rsstest.ui.MainViewModelImpl.Item
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

interface GetItemListInteractor : Interactor<Result> {

    sealed class Result {
        data class OnSuccess(val items: List<Item>) : Result()
        object OnError : Result()
    }

    suspend fun execute(limit: Int, int1: Int, int2: Int, string1: String, string2: String)
}

class GetItemListInteractorImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseInteractor<Result>(), GetItemListInteractor {

    override suspend fun execute(
        limit: Int,
        int1: Int,
        int2: Int,
        string1: String,
        string2: String
    ) {
        withContext(dispatcher) {
            try {
                onSuccess(computeList(limit, int1, int2, string1, string2))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun computeList(
        limit: Int,
        int1: Int,
        int2: Int,
        string1: String,
        string2: String
    ): List<Item> {


        return emptyList()
    }

    private fun onSuccess(songs: List<Item>) {
        liveData.value = Result.OnSuccess(songs)
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable)
        liveData.value = Result.OnError
    }
}
