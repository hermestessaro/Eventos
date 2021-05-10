package com.application.eventos.api

import com.application.eventos.model.EventModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsApi {

    @GET("events")
    suspend fun getEvents(): Response<List<EventModel>>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: Int): Response<EventModel>

    @POST("checkin")
    suspend fun doCheckin(
        @Query("eventId") eventId: String,
        @Query("name") name: String,
        @Query("email") email: String
    ): Response<Any>
}