package com.deltasoft.ihma.view.drawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.AboutUsOfficeBearersAdapter
import com.deltasoft.ihma.databinding.FragmentNewAboutUsBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.viewmodel.AboutUsViewModel
import org.checkerframework.checker.units.qual.Length


class NewAboutUsFragment : Fragment() {
    val viewModel: AboutUsViewModel by viewModels()
    var adapter_officebearers: AboutUsOfficeBearersAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aboutUsFragmentBinding = DataBindingUtil.inflate<FragmentNewAboutUsBinding>(
            inflater,
            R.layout.fragment_new_about_us,
            container,
            false
        )
        aboutUsFragmentBinding.lifecycleOwner = this
        aboutUsFragmentBinding.viewModel = viewModel
        aboutUsFragmentBinding.fragment = this

        //Office Bearers recyclerview
        adapter_officebearers = AboutUsOfficeBearersAdapter(viewModel, this)
        aboutUsFragmentBinding.officeBearersRv.adapter = adapter_officebearers
        aboutUsFragmentBinding.officeBearersRv.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )
        addData()
        observeData()
        return aboutUsFragmentBinding.root


    }



    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter_officebearers?.setData(it)
        })


    }

    private fun addData() {

        val blog= HomeModel(R.drawable.jacinda,"Jacinda")
        viewModel.add(blog)
    }

    fun onChapterClicked(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_chaptersList)

    }

}
