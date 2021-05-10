package com.application.eventos.model

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

data class EventModel (
    val people: ArrayList<String>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Float,
    val title: String,
    val id: Int
) {
    fun getFormattedDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormat.format(Date(date * 1000))
    }

    fun getFormattedPrice(): String {
        return "R$ $price"
    }
}