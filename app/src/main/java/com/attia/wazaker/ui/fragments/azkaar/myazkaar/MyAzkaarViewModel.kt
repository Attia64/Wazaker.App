package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.Azkaar
import com.attia.wazaker.database.AzkaarDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAzkaarViewModel @Inject constructor(private val azkaarDao: AzkaarDatabaseDao) : ViewModel() {

 //   private var _azkaarList = MutableLiveData<List<Azkaar>>()
    fun addZekr(passedZekr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            azkaarDao.upsert(Azkaar(0, passedZekr))
        }
    }

    val azkaarList: LiveData<List<Azkaar>> = azkaarDao.getAll()

}