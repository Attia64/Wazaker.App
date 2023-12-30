package com.attia.wazaker.ui.fragments.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attia.wazaker.database.HistoryDatabaseDao

class HistoryViewModelFactory(private val datasource: HistoryDatabaseDao) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(datasource) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}