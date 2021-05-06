package com.application.eventos.api

import com.application.eventos.model.EventModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventsService {

    private val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/events"

    private val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventsApi::class.java)

    suspend fun getEvents(): Response<List<EventModel>> {
        return api.getEvents()
    }
}