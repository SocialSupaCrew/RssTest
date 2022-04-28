package com.rsstest

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.rsstest.data.interactor.GetItemListInteractor
import com.rsstest.data.interactor.GetItemListInteractorImpl
import com.rsstest.ui.MainViewModel
import com.rsstest.ui.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel<MainViewModel> {
        MainViewModelImpl(MediatorLiveData(), MutableLiveData(), get())
    }
}

val dataModule: Module = module {
    factory<GetItemListInteractor> { GetItemListInteractorImpl() }
}

val appModule = viewModelModule + dataModule
