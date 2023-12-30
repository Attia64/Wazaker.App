package com.attia.wazaker.ui.fragments.azkaar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attia.wazaker.database.AzkaarDatabaseDao

class AzkaarViewModelFactory(private val datasource: AzkaarDatabaseDao) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AzkaarViewModel::class.java)) {
            return AzkaarViewModel(datasource) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}