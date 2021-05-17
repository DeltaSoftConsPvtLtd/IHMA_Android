package com.ilaftalkful.ihma.view.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.bindingAdaptor.NotificationAdapter
import com.ilaftalkful.ihma.databinding.FragmentSettingsBinding
import com.ilaftalkful.ihma.databinding.HomeFragmentBinding
import com.ilaftalkful.ihma.viewmodel.HomeViewModel
import com.ilaftalkful.ihma.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.home_fragment.*


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