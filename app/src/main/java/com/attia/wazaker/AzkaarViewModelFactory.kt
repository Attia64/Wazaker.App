package com.attia.wazaker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.attia.wazaker.database.AzkaarDatabaseDao

class AzkaarViewModelFactory(
    private val datasource: AzkaarDatabaseDao,
    private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AzkaarViewModel::class.java)){
            return AzkaarViewModel(datasource, application) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}