package com.ilaftalkful.ihma.view.bottomNavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.bindingAdaptor.CourseAdapter
import com.ilaftalkful.ihma.databinding.ApplyFragmentBinding
import com.ilaftalkful.ihma.model.CoursesModel
import com.ilaftalkful.ihma.viewmodel.ApplyViewModel


class ApplyFragment : Fragment() {


   
    val viewModel: ApplyViewModel by viewModels()
    var mainrecycler: RecyclerView? = null
    var adapter:CourseAdapter? = null

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
        adapter = CourseAdapter(viewModel, this)
        productsFragmentBinding.mainrecycler.adapter=adapter
        addData()
        observeData()

        return productsFragmentBinding.root
    }





    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {
        val title="Haiii"
        val description="i am using kotlin with mvvm dhfhfgh fghgfh fg fhgfhgfgh hf ggfhghgfh ghgfhhg fgh dfhfhhdf"
        if(title.isNullOrBlank()){
            Toast.makeText(context,"Enter value!", Toast.LENGTH_LONG).show()
        }else{
            val blog= CoursesModel(title,description)
            viewModel.add(blog)
        }

    }





}