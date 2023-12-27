package com.attia.wazaker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.attia.wazaker.database.HistoryDatabaseDao
import com.attia.wazaker.database.WazakerDatabase

class HistoryViewModel(
    val database: HistoryDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}