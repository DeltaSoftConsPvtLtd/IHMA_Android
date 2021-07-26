package com.deltasoft.ihma.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.HomeCollaborationsAdapter
import com.deltasoft.ihma.databinding.FragmentHomeBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.viewmodel.NewHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    val viewModel: NewHomeViewModel by viewModels()
    var adapter_collaboration: HomeCollaborationsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeFragmentBinding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        homeFragmentBinding.lifecycleOwner = this
        homeFragmentBinding.viewModel = viewModel
        homeFragmentBinding.fragment=this
        //Collaboration recyclerview
        adapter_collaboration = HomeCollaborationsAdapter(viewModel, this)
        homeFragmentBinding.collaborationRecyclerView.adapter=adapter_collaboration
        homeFragmentBinding.collaborationRecyclerView.setLayoutManager(
            LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )
        addData()
        observeData()
        return homeFragmentBinding.root
    }


    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter_collaboration?.setData(it)
        })


    }

    private fun addData() {

            val blog= HomeModel(R.drawable.ihmahomea,"")
            viewModel.add(blog)
            val blog2= HomeModel(R.drawable.ihmahomeb,"")
            viewModel.add(blog2)
            val blog3= HomeModel(R.drawable.rg,"")
            viewModel.add(blog3)

    }

    fun onAboutIHMAClicked(view: View) {

        findNavController().navigate(R.id.action_home_fragment_to_aboutIhma)

    }
    fun onHomeopathyClicked(view: View) {

        findNavController().navigate(R.id.action_home_fragment_to_homeopathy)

    }
    fun onFlashNewsClicked(view: View) {

        findNavController().navigate(R.id.action_home_fragment_to_flashnews)

    }
    fun onEventsClicked(view: View) {

        findNavController().navigate(R.id.action_home_fragment_to_events)

    }
    fun onArticlesClicked(view: View) {

       // findNavController().navigate(R.id.action_newaboutUs_fragment_to_fourthDoctor)

    }
    fun onNotificationClicked(view: View) {

        findNavController().navigate(R.id.action_home_fragment_to_notification)

    }

    }



