package com.application.eventos.adapters

import android.view.View
import com.application.eventos.model.EventModel

interface EventClickListener {
    fun onEventClicked(view: View, event: EventModel)
}