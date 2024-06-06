package com.attia.wazaker.ui.fragments.counter


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.ui.fragments.history.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    ViewModel() {

    private var checkCount = 0
    var flag = false
    private var time = 0
    private var tasbihJob: Job? = null
    private var timingJob: Job? = null
    private var  lauchCalled = false
    var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        _counter.value = step.value?.let { (_counter.value)?.plus(it) }
        if (flag && checkCount < 3) {
            checkCount++
        }
        if(checkCount == 3 && !lauchCalled) {
            lauchCalled = true
            flag = false
            timingJob?.cancel()
            launchTasbih()
        }
    }

    fun restCounter() {
        _counter.value = 0
    }

    fun addHistory(zekr: String, date: String, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.addHistory(zekr, date, count)
        }
    }

    fun automationTasbih() {
        flag = true
        lauchCalled  = false
        timingJob = processDuration()
        timingJob?.start()
    }

    private fun launchTasbih() {
        Log.i("AutomationTasbih", "duration : $time")
        tasbihJob = automatedTasbihProcess(duration = (time / 3) * 1000L)
        tasbihJob?.start()
    }

    private fun automatedTasbihProcess(duration: Long): Job {
        return viewModelScope.launch {
            while (isActive) {
                delay(duration)
                increaseCounter()
            }
        }
    }
    private fun processDuration(): Job {
        return viewModelScope.launch {
            while (flag) {
                delay(1000)
                time++
            }
        }
    }

    fun cancelAutomationTasbih() {
        tasbihJob?.cancel()
        checkCount = 0
        time = 0
        lauchCalled = false
    }
}