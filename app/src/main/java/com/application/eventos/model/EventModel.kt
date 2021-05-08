package com.application.eventos.model

import java.sql.Timestamp
import java.util.*

data class EventModel (
    val people: ArrayList<String>,
    val date: String,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Float,
    val title: String,
    val id: Int
)