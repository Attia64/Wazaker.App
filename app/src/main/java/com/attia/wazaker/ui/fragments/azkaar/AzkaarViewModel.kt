package com.attia.wazaker.ui.fragments.azkaar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.Azkaar
import com.attia.wazaker.database.AzkaarDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AzkaarViewModel(val database: AzkaarDatabaseDao) : ViewModel() {

    fun addZekr(passedZekr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            database.upsert(Azkaar(0, passedZekr))
        }
    }
}