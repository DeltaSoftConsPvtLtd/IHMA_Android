package com.deltasoft.ihma.view.login

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.base.IlafBaseActivity
import com.deltasoft.ihma.base.IlafBaseFragment
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentEnterEmailBinding
import com.deltasoft.ihma.databinding.LoginFragmentBinding
import com.deltasoft.ihma.model.SignInErrors
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.utilities.Utility
import com.deltasoft.ihma.view.LoginViewModel
import com.deltasoft.ihma.view.SplashActivity
import com.deltasoft.ihma.viewmodel.EnterEmailViewModel
import kotlinx.android.synthetic.main.login_fragment.view.*
import java.util.Observer


class EnterEmailFragment : IlafBaseFragment() {
    val viewModel: EnterEmailViewModel by viewModels()
    var emailFragmentBinding: FragmentEnterEmailBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        emailFragmentBinding = DataBindingUtil.inflate<FragmentEnterEmailBinding>(
            inflater,
            R.layout.fragment_enter_email,
            container,
            false
        )
        emailFragmentBinding?.lifecycleOwner = this
        emailFragmentBinding?.viewModel = viewModel
        emailFragmentBinding?.fragment = this
        return emailFragmentBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLiveData?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }

                UserData.UserStatus.USER_OTP_SUCCESS -> {
                    findNavController().navigate(R.id.action_email_otpFragment)
                }

                UserData.UserStatus.USER_OTP_FAILED -> {
                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.otp_sending_failed),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()
                }


            }
        })
    }





    fun onGetEmail(view: View) {
        viewModel.callOTP()
    }
}

