package com.rsstest.core.domain

import androidx.lifecycle.LiveData

interface Interactor<T> {

    fun getLiveData(): LiveData<T>
}
