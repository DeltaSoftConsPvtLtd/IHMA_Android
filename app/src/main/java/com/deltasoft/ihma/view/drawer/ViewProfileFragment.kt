package com.deltasoft.ihma.view.drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentViewProfileBinding
import com.deltasoft.ihma.viewmodel.ProfileViewModel


class ViewProfileFragment : Fragment() {

    val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsFragmentBinding = DataBindingUtil.inflate<FragmentViewProfileBinding>(
            inflater,
            R.layout.fragment_view_profile,
            container,
            false
        )
        productsFragmentBinding.lifecycleOwner = this
        productsFragmentBinding.viewModel = viewModel
        return productsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}