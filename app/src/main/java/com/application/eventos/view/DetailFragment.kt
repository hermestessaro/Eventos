package com.application.eventos.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.application.eventos.R
import com.application.eventos.databinding.FragmentDetailBinding
import com.application.eventos.util.loadImage
import com.application.eventos.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.item_event.*

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(EventsViewModel::class.java)

        binding.checkinButton.setOnClickListener {
            showDialog()
        }

        observeViewModel()
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(context).setTitle(context?.getString(R.string.dialog_title))
        val view = requireActivity().layoutInflater.inflate(R.layout.custom_dialog, null)
        builder.setView(view)
        builder.setPositiveButton("Check in") { _, _ ->
            val name = view.name_input.text.toString()
            val email = view.email_input.text.toString()
            if (name != "" && email != "") {
                viewModel.doCheckin(viewModel.selectedEvent.value?.id.toString(), name, email)
            } else {
                Toast.makeText(
                    context,
                    context?.getString(R.string.empty_input),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.show()
    }

    private fun observeViewModel(){
        viewModel.selectedEvent.observe(viewLifecycleOwner, {
            binding.event = it
            binding.notifyChange()
        })

        viewModel.eventsLoadError.observe(viewLifecycleOwner, {
            if(it){
                binding.eventError.visibility = View.VISIBLE
            } else {
                binding.eventError.visibility = View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if(it){
                binding.loadingView.visibility = View.VISIBLE
                binding.eventError.visibility = View.GONE
            } else {
                binding.loadingView.visibility = View.GONE
            }
        })
    }
}