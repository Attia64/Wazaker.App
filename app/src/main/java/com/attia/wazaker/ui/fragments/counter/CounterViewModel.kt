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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    ViewModel() {

    private var checkCount = 0
    var flag = false
    private var processDuration = 0L
    private var tasbihJob: Job? = null

    var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        _counter.value = step.value?.let { (_counter.value)?.plus(it) }
        if (flag) {
            checkCount++
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

    private fun checker(): Boolean {
        return checkCount == 3
    }

    private fun setEndTime(): Long {
        if (checker()) {
            return System.currentTimeMillis()
        }
        return 0L
    }

    fun automationTasbih() {
        val startTime = System.currentTimeMillis()
        flag = true
        val endTime = setEndTime()
        processDuration = ((endTime - startTime) / 1000) / 3
        Log.i("AutomationTasbih", "StartTime: $startTime & EndTime: $endTime")
        tasbihJob = automatedTasbihProcess(processDuration)
        flag = false
        checkCount = 0

    }

    private fun automatedTasbihProcess(duration: Long): Job {
        return viewModelScope.launch {
            while (true) {
                delay(duration)
                increaseCounter()
            }
        }
    }

    fun cancelAutomationTasbih() {
        tasbihJob?.cancel()
    }
}