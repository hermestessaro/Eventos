package com.application.eventos.model

import java.util.*

data class EventModel (
    val people: String,
    val date: Date,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Float,
    val title: String
)