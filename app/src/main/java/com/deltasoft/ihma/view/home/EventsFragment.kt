package com.deltasoft.ihma.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.EventsAdapter
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentEventsBinding
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.view.splash.SplashActivity
import com.deltasoft.ihma.viewmodel.EventsViewModel


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
        viewModel.mutableEventlst.observe(viewLifecycleOwner, Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {
        viewModel.addEventsApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }
                UserData.UserStatus.EMPTY_EVENTS -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.no_events),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()

                }
                UserData.UserStatus.EVENTS_FAILED -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.failed_events),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()

                }


            }
        })
    }
}






