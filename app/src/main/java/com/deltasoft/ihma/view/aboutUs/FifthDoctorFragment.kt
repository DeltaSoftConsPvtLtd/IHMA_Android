package com.deltasoft.ihma.view.aboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentFifthDoctorBinding



class FifthDoctorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val doctorFragmentBinding = DataBindingUtil.inflate<FragmentFifthDoctorBinding>(inflater,R.layout.fragment_fifth_doctor, container, false)
        doctorFragmentBinding.lifecycleOwner=this
        return doctorFragmentBinding.root
    }
}