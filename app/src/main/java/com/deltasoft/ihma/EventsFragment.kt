package com.deltasoft.ihma

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deltasoft.ihma.bindingAdaptor.EventsAdapter
import com.deltasoft.ihma.bindingAdaptor.NotificationAdapter
import com.deltasoft.ihma.databinding.FragmentEventsBinding
import com.deltasoft.ihma.databinding.FragmentMyCoursesBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.model.NotificationModel
import com.deltasoft.ihma.viewmodel.EventsViewModel
import com.deltasoft.ihma.viewmodel.MyCoursesViewModel


class EventsFragment : Fragment() {
    val viewModel: EventsViewModel by viewModels()
    var adapter: EventsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val eventsFragmentBinding = DataBindingUtil.inflate<FragmentEventsBinding>(
            inflater,
            R.layout.fragment_events,
            container,
            false
        )
        eventsFragmentBinding.lifecycleOwner = this
        eventsFragmentBinding.viewModel = viewModel
        adapter = EventsAdapter(viewModel, this)
        eventsFragmentBinding.eventsRecyclerView.adapter=adapter
        addData()
        observeData()

        return eventsFragmentBinding.root
    }

    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {

        val description="Events"

            val event= HomeModel(1,description)
            viewModel.add(event)
        }

    }



