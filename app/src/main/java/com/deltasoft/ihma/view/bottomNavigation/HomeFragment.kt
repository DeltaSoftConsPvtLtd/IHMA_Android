package com.deltasoft.ihma.view.bottomNavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.HomeCollaborationsAdapter
import com.deltasoft.ihma.bindingAdaptor.HomeResearchAdapter
import com.deltasoft.ihma.databinding.FragmentHomeBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.viewmodel.NewHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    val viewModel: NewHomeViewModel by viewModels()
    var adapter_collaboration: HomeCollaborationsAdapter? = null
    var adapter_research: HomeResearchAdapter? = null

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

        //Research recyclerview
        adapter_research = HomeResearchAdapter(viewModel, this)
        homeFragmentBinding.researchRecyclerview.adapter=adapter_research
        homeFragmentBinding.researchRecyclerview.setLayoutManager(
            GridLayoutManager(activity,3)
        )
        addDataResearch()
        observeData()

        return homeFragmentBinding.root
    }


    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter_collaboration?.setData(it)
        })

        viewModel.researchLst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter_research?.setData(it)
        })
    }

    private fun addData() {

            val blog= HomeModel(R.drawable.jacinda,"Jacinda")
            viewModel.add(blog)
        }

    private fun addDataResearch() {

        val blog1= HomeModel(R.drawable.publication_icon,"About IHMA")
        viewModel.addResearch(blog1)
        val blog2= HomeModel(R.drawable.training_icon,"Homeopathy")
        viewModel.addResearch(blog2)
        val blog3= HomeModel(R.drawable.conferene_icon,"Flash News")
        viewModel.addResearch(blog3)
        val blog4= HomeModel(R.drawable.events_icon,"Events")
        viewModel.addResearch(blog4)
        val blog5= HomeModel(R.drawable.conferene_icon,"Articles")
        viewModel.addResearch(blog5)
        val blog6= HomeModel(R.drawable.events_icon,"Institutions")
        viewModel.addResearch(blog6)
    }

    }



