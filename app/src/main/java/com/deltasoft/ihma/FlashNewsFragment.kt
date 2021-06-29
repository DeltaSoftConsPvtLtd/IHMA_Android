package com.deltasoft.ihma

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deltasoft.ihma.bindingAdaptor.EventsAdapter
import com.deltasoft.ihma.bindingAdaptor.FlashNewsAdapter
import com.deltasoft.ihma.databinding.FragmentEventsBinding
import com.deltasoft.ihma.databinding.FragmentFlashNewsBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.viewmodel.EventsViewModel
import com.deltasoft.ihma.viewmodel.FlasNewsViewModel


class FlashNewsFragment : Fragment() {
    val viewModel: FlasNewsViewModel by viewModels()
    var adapter: FlashNewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val flashnewsFragmentBinding = DataBindingUtil.inflate<FragmentFlashNewsBinding>(
            inflater,
            R.layout.fragment_flash_news,
            container,
            false
        )
        flashnewsFragmentBinding.lifecycleOwner = this
        flashnewsFragmentBinding.viewModel = viewModel
        adapter = FlashNewsAdapter(viewModel, this)
        flashnewsFragmentBinding.flashnewsRecyclerView.adapter=adapter
        addData()
        observeData()

        return flashnewsFragmentBinding.root
    }





    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {

        val description="Flash News "

        val event= HomeModel(1,description)
        viewModel.add(event)
    }

}

