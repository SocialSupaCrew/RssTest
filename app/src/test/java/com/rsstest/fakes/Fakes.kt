package com.rsstest.fakes

import com.rsstest.ui.MainViewModelImpl.Item

fun fakeItemList(): List<Item> {
    return listOf(
        Item(1, "1"),
        Item(2, "2"),
        Item(3, "fizz"),
        Item(4, "4"),
        Item(5, "buzz"),
        Item(6, "fizz"),
        Item(7, "7"),
        Item(8, "8"),
        Item(9, "fizz"),
        Item(10, "buzz"),
        Item(11, "11"),
        Item(12, "fizz"),
        Item(13, "13"),
        Item(14, "14"),
        Item(15, "fizzbuzz"),
        Item(16, "16"),
        Item(17, "17"),
        Item(18, "fizz"),
        Item(19, "19"),
        Item(20, "buzz")
    )
}
