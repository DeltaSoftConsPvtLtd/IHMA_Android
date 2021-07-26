package com.deltasoft.ihma.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentHomeopathyBinding


class HomeopathyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeopathyFragmentBinding = DataBindingUtil.inflate<FragmentHomeopathyBinding>(inflater,R.layout.fragment_homeopathy, container, false)
        homeopathyFragmentBinding.lifecycleOwner=this
        homeopathyFragmentBinding?.fragment = this
        return homeopathyFragmentBinding.root
    }
}