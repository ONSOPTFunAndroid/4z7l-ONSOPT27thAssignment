package com.igluesmik.sopt.ui.view.itemtouch

interface ItemTouchHelperListener {
    fun onItemMoved(from : Int, to : Int)
    fun onItemSwiped(position : Int)
}