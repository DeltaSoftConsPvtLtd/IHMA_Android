package com.deltasoft.ihma.view.aboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentSeventhDoctorBinding
import com.deltasoft.ihma.viewmodel.SeventhDoctorViewModel


class SeventhDoctorFragment : Fragment() {
    val viewModel: SeventhDoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val doctorFragmentBinding = DataBindingUtil.inflate<FragmentSeventhDoctorBinding>(inflater,R.layout.fragment_seventh_doctor, container, false)
        doctorFragmentBinding.lifecycleOwner=this
        doctorFragmentBinding?.fragment = this
        return doctorFragmentBinding.root
    }


}