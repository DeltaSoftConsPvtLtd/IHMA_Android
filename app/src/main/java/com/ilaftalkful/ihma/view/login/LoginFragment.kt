package com.ilaftalkful.ihma.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.ilaftalkful.ihma.LogInHandler
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.base.IlafBaseFragment
import com.ilaftalkful.ihma.databinding.LoginFragmentBinding
import com.ilaftalkful.ihma.utilities.IlafSharedPreference
import com.ilaftalkful.ihma.view.LoginViewModel
import java.util.*


class LoginFragment : IlafBaseFragment(), LogInHandler {

    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginFragmentBinding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater,R.layout.login_fragment, container, false)
        loginFragmentBinding.lifecycleOwner=this
        loginFragmentBinding.viewModel=viewModel
        loginFragmentBinding.fragment=this
        loginFragmentBinding.handler =this
        return loginFragmentBinding.root



    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Watching for login result
        viewModel.getLogInResult().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result->

            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        })
    }

    fun onGuestClicked(view:View){
        IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN,true)
       findNavController().navigate(R.id.action_show_home_guest)
    }
    fun onRegisterClicked(view:View){
        findNavController().navigate(R.id.action_show_register)
    }
    fun onResetPasswordClicked(view:View){
        findNavController().navigate(R.id.action_login_fragment_to_resetPasswordFragment)
    }
//
//    fun onLoginClicked(view:View){
//        IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN,false)
//        findNavController().navigate(R.id.action_show_home_guest)
//    }



    override fun onLogInClicked() {
        viewModel.performValidation()
    }


}



