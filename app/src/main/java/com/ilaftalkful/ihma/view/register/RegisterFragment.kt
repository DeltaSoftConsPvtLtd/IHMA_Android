package com.ilaftalkful.ihma.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.base.IlafBaseFragment
import com.ilaftalkful.ihma.databinding.LoginFragmentBinding
import com.ilaftalkful.ihma.databinding.RegisterFragmentBinding
import com.ilaftalkful.ihma.viewmodel.RegisterViewModel

class RegisterFragment : IlafBaseFragment() {

    val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val registerFragmentBinding = DataBindingUtil.inflate<RegisterFragmentBinding>(inflater,R.layout.register_fragment, container, false)
        registerFragmentBinding.lifecycleOwner=this
        registerFragmentBinding.viewModel=viewModel
        return registerFragmentBinding.root
    }

}