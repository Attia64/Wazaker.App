package com.attia.wazaker.ui.fragments.counter


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.AzkaarHistory
import com.attia.wazaker.database.HistoryDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CounterViewModel @Inject constructor(@Named("DaoHistory") private val historyDao: HistoryDatabaseDao) :
    ViewModel() {

    private var checkCount = 0
    var flag = false
    private var endTime = 0L
    private var processDuration = 0L

    var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        _counter.value = step.value?.let { (_counter.value)?.plus(it) }
        if (flag && checkCount < 3) {
            checkCount++
        }
    }

    fun restCounter() {
        _counter.value = 0
    }

    fun addHistory(zekr: String, date: String, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            historyDao.upsertHistoryField(AzkaarHistory(0, zekr, date, count))
        }
    }

    private fun setEndTime() {
        if(checkCount == 3){
            endTime = System.currentTimeMillis()
        }
    }
    fun automationTasbih() {
        val startTime = System.currentTimeMillis()
        setEndTime()
        if (checkCount == 3) {
            processDuration = ((endTime - startTime) / 1000) / 3
            automatedTasbihProcess(processDuration)
        }

    }

    private fun automatedTasbihProcess(duration: Long) {
        viewModelScope.launch {
            while (flag) {
                delay(duration)
                _counter.value = step.value?.let { (_counter.value)?.plus(it) }
            }
        }
    }
}