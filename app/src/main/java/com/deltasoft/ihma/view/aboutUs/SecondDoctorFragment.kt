package com.deltasoft.ihma.view.aboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentSecondDoctorBinding


class SecondDoctorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val doctorFragmentBinding = DataBindingUtil.inflate<FragmentSecondDoctorBinding>(inflater,R.layout.fragment_second_doctor, container, false)
        doctorFragmentBinding.lifecycleOwner=this
        doctorFragmentBinding?.fragment = this
        return doctorFragmentBinding.root
    }
}