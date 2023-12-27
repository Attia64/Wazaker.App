package com.attia.wazaker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attia.wazaker.database.HistoryDatabaseDao

class HistoryViewModelFactory(
    private val datasource: HistoryDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            return HistoryViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}