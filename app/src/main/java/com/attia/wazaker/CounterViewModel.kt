package com.attia.wazaker

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale


class CounterViewModel: ViewModel() {

   var step = MutableLiveData<Int>()

    private var _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init{
        _counter.value = 0
        step.value = 1

    }
    fun increaseCounter(){
        val added = step.value
       _counter.value = added?.let { (_counter.value)?.plus(it) }
    }
    fun restCounter(){
        _counter.value = 0
    }
}