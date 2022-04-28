package com.rsstest.data.interactor

import com.rsstest.core.domain.BaseInteractor
import com.rsstest.core.domain.Interactor
import com.rsstest.data.interactor.GetItemListInteractor.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

interface GetItemListInteractor : Interactor<Result> {

    sealed class Result {
        data class OnSuccess(val items: List<String>) : Result()
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

            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun onSuccess(songs: List<String>) {
        liveData.value = Result.OnSuccess(songs)
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable)
        liveData.value = Result.OnError
    }
}
