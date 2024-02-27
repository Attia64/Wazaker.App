package com.attia.wazaker.ui.fragments.counter

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {


    private var check = 0
    private var seconds = 0L

    var automated = false
    var isRunning = false

    var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        if (!isRunning) {
            isRunning = true
        }
        _counter.value = step.value?.let { (_counter.value)?.plus(it) }

        if(automated && check < 4){
            check++
        }
    }

    fun restCounter() {
        _counter.value = 0
    }

    fun runAutomationTasbih() {

        val handler = Handler()
        if(check < 4) {
            handler.postDelayed({
                seconds += 1000
            }, 1000)
        }

        handler.postDelayed({
            _counter.value = step.value?.let { (_counter.value)?.plus(it) }
        }, seconds)
    }

}