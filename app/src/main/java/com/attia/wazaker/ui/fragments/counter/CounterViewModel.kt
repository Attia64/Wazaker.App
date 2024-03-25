package com.attia.wazaker.ui.fragments.counter


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.AzkaarHistory
import com.attia.wazaker.database.HistoryDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CounterViewModel @Inject constructor(@Named("DaoHistory") private val historyDao: HistoryDatabaseDao) :
    ViewModel() {


    var step = MutableLiveData(1)

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        _counter.value = step.value?.let { (_counter.value)?.plus(it) }
    }

    fun restCounter() {
        _counter.value = 0
    }

    fun addHistory(zekr: String, date: String , count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            historyDao.upsertHistoryField(AzkaarHistory(0, zekr, date, count))
        }
    }


}