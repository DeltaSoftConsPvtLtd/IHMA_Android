package com.ilaftalkful.ihma.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.databinding.HomeFragmentBinding
import com.ilaftalkful.ihma.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.view.*

class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()
    var contactFragmentBinding: HomeFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         contactFragmentBinding = DataBindingUtil.inflate<HomeFragmentBinding>(inflater,R.layout.home_fragment, container, false)
         contactFragmentBinding?.lifecycleOwner=this
         contactFragmentBinding?.viewModel = viewModel
         return contactFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}