package com.deltasoft.ihma.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentSettingsBinding
import com.deltasoft.ihma.viewmodel.SettingsViewModel



class SettingsFragment : Fragment() {

    val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contactFragmentBinding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,R.layout.fragment_settings, container, false)
        contactFragmentBinding.lifecycleOwner=this
        return contactFragmentBinding.root
    }


}