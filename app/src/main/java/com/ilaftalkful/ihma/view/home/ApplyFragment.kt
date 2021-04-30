package com.ilaftalkful.ihma.view.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.bindingAdaptor.CourseAdapter
import com.ilaftalkful.ihma.databinding.ApplyFragmentBinding
import com.ilaftalkful.ihma.viewmodel.ApplyViewModel
import com.ilaftalkful.ihma.model.CoursesModel

class ApplyFragment : Fragment() {

    private var viewManager = LinearLayoutManager(context)
    // lateinit var mainrecycler: RecyclerView
    val viewModel: ApplyViewModel by viewModels()
    var mainrecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsFragmentBinding = DataBindingUtil.inflate<ApplyFragmentBinding>(
            inflater,
            R.layout.apply_fragment,
            container,
            false
        )
        productsFragmentBinding.lifecycleOwner = this
        productsFragmentBinding.viewModel = viewModel
        productsFragmentBinding.mainrecycler
        // val factory = ApplyViewModelFactory()
        addData()
        initialiseAdapter()

        return productsFragmentBinding.root
    }

    private fun initialiseAdapter() {
        mainrecycler?.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            mainrecycler?.adapter = CourseAdapter(viewModel, it, this)
        })
    }

    private fun addData() {

        val title="Haiii"
        val description="i am using kotlin with mvvm"
        if(title.isNullOrBlank()){
            Toast.makeText(context,"Enter value!", Toast.LENGTH_LONG).show()
        }else{
            val blog= CoursesModel(title,description)
            viewModel.add(blog)
            mainrecycler?.adapter?.notifyDataSetChanged()
        }

    }


}