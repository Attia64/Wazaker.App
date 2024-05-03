package com.attia.wazaker.ui.fragments.history

import androidx.lifecycle.LiveData
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
class HistoryViewModel @Inject constructor(@Named("DaoHistory") private val historyDatabaseDao: HistoryDatabaseDao) :
    ViewModel() {

        fun deleteHistoryItem(item: AzkaarHistory) {
            viewModelScope.launch (Dispatchers.IO){
                historyDatabaseDao.delete(item)
            }
        }

    val historyList: LiveData<List<AzkaarHistory>> = historyDatabaseDao.getAll()
}