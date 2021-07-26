package com.deltasoft.ihma.view.aboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentSixthDoctorBinding


class SixthDoctorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val doctorFragmentBinding = DataBindingUtil.inflate<FragmentSixthDoctorBinding>(inflater,R.layout.fragment_sixth_doctor, container, false)
        doctorFragmentBinding.lifecycleOwner=this
        doctorFragmentBinding?.fragment = this
        return doctorFragmentBinding.root
    }
}