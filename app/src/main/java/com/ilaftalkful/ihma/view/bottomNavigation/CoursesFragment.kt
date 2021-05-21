package com.ilaftalkful.ihma.view.bottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.databinding.CoursesFragmentBinding
import com.ilaftalkful.ihma.viewmodel.HomeViewModel

class CoursesFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()
    var contactFragmentBinding: CoursesFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         contactFragmentBinding = DataBindingUtil.inflate<CoursesFragmentBinding>(inflater,R.layout.courses_fragment, container, false)
         contactFragmentBinding?.lifecycleOwner=this
         contactFragmentBinding?.viewModel = viewModel
         return contactFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}