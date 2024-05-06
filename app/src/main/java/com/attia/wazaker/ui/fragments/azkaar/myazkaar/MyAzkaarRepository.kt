package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import androidx.lifecycle.LiveData
import com.attia.wazaker.database.Azkaar
import com.attia.wazaker.database.AzkaarDatabaseDao
import javax.inject.Inject
import javax.inject.Named

class MyAzkaarRepository @Inject constructor(@Named("DaoAzkaar") private val azkaarDao: AzkaarDatabaseDao) {

    suspend fun addZekr(passedZekr: String) {
        azkaarDao.upsert(Azkaar(0, passedZekr))
    }

    suspend fun deleteItem(zekr: Azkaar) {
        azkaarDao.deletezekr(zekr)
    }

    fun getAzkaarList(): LiveData<List<Azkaar>> {
        return azkaarDao.getAll()
    }

}