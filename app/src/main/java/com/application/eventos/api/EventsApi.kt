package com.application.eventos.api

import com.application.eventos.model.EventModel
import retrofit2.Response
import retrofit2.http.GET

interface EventsApi {

    @GET("events")
    suspend fun getEvents(): Response<List<EventModel>>
}