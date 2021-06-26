package com.deltasoft.ihma.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentForgetPasswordBinding
import com.deltasoft.ihma.databinding.FragmentSettingsBinding
import com.deltasoft.ihma.viewmodel.ForgotPasswordViewModel
import com.deltasoft.ihma.viewmodel.SettingsViewModel


class ForgetPasswordFragment : Fragment() {
    val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contactFragmentBinding = DataBindingUtil.inflate<FragmentForgetPasswordBinding>(inflater,R.layout.fragment_forget_password, container, false)
        contactFragmentBinding.lifecycleOwner=this
        contactFragmentBinding?.fragment = this
        return contactFragmentBinding.root
    }

    fun onLoginClicked(view: View) {
        findNavController().navigate(R.id.action_forgetPasswordFragment_login)
    }
}