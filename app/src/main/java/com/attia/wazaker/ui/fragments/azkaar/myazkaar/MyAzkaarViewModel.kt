package com.attia.wazaker.ui.fragments.azkaar.myazkaar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attia.wazaker.database.Azkaar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAzkaarViewModel @Inject constructor(private val myAzkaarRepository: MyAzkaarRepository) :
    ViewModel() {

    fun addZekr(passedZekr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            myAzkaarRepository.addZekr(passedZekr)
        }
    }

    fun deleteItem(zekr: Azkaar) {
        viewModelScope.launch(Dispatchers.IO) {
            myAzkaarRepository.deleteItem(zekr)
        }
    }

    val azkaarList: LiveData<List<Azkaar>> = myAzkaarRepository.getAzkaarList()

}