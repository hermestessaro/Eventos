package com.application.eventos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.eventos.api.EventsService
import com.application.eventos.model.EventModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val eventsService = EventsService()

    val events = MutableLiveData<List<EventModel>>()
    val eventsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = eventsService.getEvents()
            if(response.isSuccessful){
                events.postValue(response.body())
                eventsLoadError.postValue(false)
                loading.postValue(false)
            } else {
                loading.postValue(false)

            }
        }
    }
}