package com.application.eventos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.eventos.api.EventsService
import com.application.eventos.model.EventModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel: ViewModel() {

    private val eventsService = EventsService()

    val events = MutableLiveData<List<EventModel>>()
    val selectedEvent = MutableLiveData<EventModel>()
    val eventsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    fun getEvent(id: Int){
        fetchSpecificEvent(id)
    }

    fun doCheckin(id: String, name: String, email: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = eventsService.doCheckin(id, name, email)
        }
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

    private fun fetchSpecificEvent(id: Int){
        loading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = eventsService.getEventById(id)
            if(response.isSuccessful){
                selectedEvent.postValue(response.body())
                eventsLoadError.postValue(false)
                loading.postValue(false)
            } else {
                loading.postValue(false)
            }
        }
    }
}