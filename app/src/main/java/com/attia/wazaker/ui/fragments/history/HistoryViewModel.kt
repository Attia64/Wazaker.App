package com.attia.wazaker.ui.fragments.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.AzkaarHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    ViewModel() {

    fun deleteHistoryItem(item: AzkaarHistory) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.deleteHistoryItem(item)
        }
    }

    val historyList: LiveData<List<AzkaarHistory>> = historyRepository.getHistoryList()
}