package com.attia.wazaker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.Azkaar
import com.attia.wazaker.database.AzkaarDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AzkaarViewModel(
    val database: AzkaarDatabaseDao,
    application: Application
): AndroidViewModel(application) {

    lateinit var passedZekr: String

    //private var viewModelJob = Job()

    private var newzekr = Azkaar(0, passedZekr)
    /*
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var currentZekr = MutableLiveData<Azkaar>()
    private val Azkar = database.getAll()
     */

     fun addZekr(){
        viewModelScope.launch(Dispatchers.IO) {
            database.upsert(newzekr)
        }
    }

}