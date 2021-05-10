package com.application.eventos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.Navigation
import com.application.eventos.R
import com.application.eventos.databinding.ItemEventBinding
import com.application.eventos.model.EventModel
import com.application.eventos.view.ListFragmentDirections
import com.application.eventos.viewmodel.EventsViewModel

class EventAdapter(
    private val eventsList: ArrayList<EventModel>,
    private val viewModel: EventsViewModel
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>(),
    EventClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemEventBinding>(inflater, R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.view.event = eventsList[position]
        holder.view.listener = this
    }

    override fun getItemCount() = eventsList.size

    fun updateEventList(newEventList: List<EventModel>) {
        eventsList.clear()
        eventsList.addAll(newEventList)
        notifyDataSetChanged()
    }

    override fun onEventClicked(view: View, event: EventModel) {
        viewModel.selectedEvent.postValue(event)
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        Navigation.findNavController(view).navigate(action)
    }

    class EventViewHolder(var view: ItemEventBinding) : RecyclerView.ViewHolder(view.root)


}