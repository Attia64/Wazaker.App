package com.attia.wazaker.ui.fragments.history

import androidx.lifecycle.LiveData
import com.attia.wazaker.database.AzkaarHistory
import com.attia.wazaker.database.HistoryDatabaseDao
import javax.inject.Inject
import javax.inject.Named

class HistoryRepository @Inject constructor(@Named("DaoHistory") private val historyDatabaseDao: HistoryDatabaseDao) {


    suspend fun addHistory(zekr: String, date: String, count: Int) {
        historyDatabaseDao.upsertHistoryField(AzkaarHistory(0, zekr, date, count))
    }

    suspend fun deleteHistoryItem(item: AzkaarHistory) {
        historyDatabaseDao.delete(item)
    }

    fun getHistoryList(): LiveData<List<AzkaarHistory>> {
        return historyDatabaseDao.getAll()
    }
}
