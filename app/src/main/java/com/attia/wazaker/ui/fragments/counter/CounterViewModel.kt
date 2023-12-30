package com.attia.wazaker.ui.fragments.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

   var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter(){
        val added = step.value
       _counter.value = added?.let { (_counter.value)?.plus(it) }
    }
    fun restCounter(){
        _counter.value = 0
    }
}