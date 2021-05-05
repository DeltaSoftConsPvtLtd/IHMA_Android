package com.ilaftalkful.ihma.view.bottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.databinding.JoinFragmentBinding
import com.ilaftalkful.ihma.viewmodel.JoinViewModel

class JoinFragment : Fragment() {


    val viewModel: JoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myLogsFragmentBinding = DataBindingUtil.inflate<JoinFragmentBinding>(inflater,R.layout.join_fragment, container, false)
        myLogsFragmentBinding.lifecycleOwner=this
        myLogsFragmentBinding.viewModel=viewModel
        return myLogsFragmentBinding.root
    }

}