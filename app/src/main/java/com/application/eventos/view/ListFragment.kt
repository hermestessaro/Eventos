package com.application.eventos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.eventos.R
import com.application.eventos.adapters.EventAdapter
import com.application.eventos.databinding.FragmentListBinding
import com.application.eventos.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private val eventsAdapter = EventAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()

        binding.eventsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventsAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.eventsList.visibility = View.GONE
            binding.listError.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.events.observe(viewLifecycleOwner, { events ->
            events?.let {
                binding.eventsList.visibility = View.VISIBLE
                eventsAdapter.updateEventList(it)
            }
        })

        viewModel.eventsLoadError.observe(viewLifecycleOwner, {
            if(it){
                binding.listError.visibility = View.VISIBLE
            } else {
                binding.listError.visibility = View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if(it){
                binding.loadingView.visibility = View.VISIBLE
                binding.listError.visibility = View.GONE
                binding.eventsList.visibility = View.GONE
            } else {
                binding.loadingView.visibility = View.GONE
            }
        })
    }
}