package com.rsstest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.rsstest.core.EventPath
import com.rsstest.data.interactor.GetItemListInteractor
import com.rsstest.data.interactor.GetItemListInteractor.Result
import com.rsstest.fakes.fakeItemList
import com.rsstest.ui.MainViewModel.Path
import com.rsstest.ui.MainViewModel.State
import com.rsstest.ui.MainViewModelImpl.Item
import com.rsstest.utils.assertLiveData
import com.rsstest.rules.MainCoroutineRule
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

@ExperimentalCoroutinesApi
class MainViewModelImplTest : KoinTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getItemListInteractor: GetItemListInteractor = mockk()

    private val items: List<Item> = fakeItemList()

    private val state: MediatorLiveData<State> = MediatorLiveData()
    private val navigation: MutableLiveData<EventPath<Path>> = MutableLiveData()
    private val getItemListInteractorLiveData: MutableLiveData<Result> = MutableLiveData()

    private val testModule: Module = module {
        viewModel { MainViewModelImpl(state, navigation, getItemListInteractor) }
    }

    private lateinit var subject: MainViewModelImpl

    @Before
    fun setUp() {
        startKoin { modules(testModule) }
        every { getItemListInteractor.getLiveData() } returns getItemListInteractorLiveData
        subject = get()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `computeItem - execute`() = runTest {
        `when items are compute`()
        `then interactor should be executed`()
    }

    @Test
    fun `computeItem - success`() = runTest {
        `when compute items has result`(Result.OnSuccess(items))
        `then state observer should receive state`(State.ItemListLoaded(items))
    }

    @Test
    fun `computeItem - error`() = runTest {
        `when compute items has result`(Result.OnError)
        `then state observer should receive state`(State.ShowError)
    }

    private fun `when items are compute`() {
        subject.computeListItem(0, 0, 0, "", "")
    }

    private fun `when compute items has result`(result: Result) {
        getItemListInteractorLiveData.value = result
    }

    private fun `then interactor should be executed`() {
        coVerify { getItemListInteractor.execute(0, 0, 0, "", "") }
    }

    private fun `then state observer should receive state`(state: State) {
        assertLiveData(subject.getState()).isEqualTo(state)
    }
}
