package com.rsstest.data.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rsstest.data.interactor.GetItemListInteractor.Result.OnSuccess
import com.rsstest.fakes.fakeItemList
import com.rsstest.utils.assertLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetItemListInteractorImplTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var subject: GetItemListInteractorImpl

    private val dispatcher = StandardTestDispatcher(TestCoroutineScheduler())
    private val itemList = fakeItemList()

    @Before
    fun setUp() {
        subject = GetItemListInteractorImpl(dispatcher)
    }

    @Test
    fun `should compute item list`() = runTest(dispatcher) {
        subject.execute(20, 3, 5, "fizz", "buzz")
        advanceUntilIdle()

        assertLiveData(subject.getLiveData()).isEqualTo(OnSuccess(itemList))
    }
}
