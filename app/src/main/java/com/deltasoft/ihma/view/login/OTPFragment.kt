package com.deltasoft.ihma.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentOTPBinding
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.model.loginmodel.Data
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.view.SplashActivity
import com.deltasoft.ihma.viewmodel.OTPViewModel


class OTPFragment : Fragment() {


    val viewModel: OTPViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val otpFragmentBinding = DataBindingUtil.inflate<FragmentOTPBinding>(inflater,R.layout.fragment_o_t_p, container, false)
        otpFragmentBinding.lifecycleOwner=this
        otpFragmentBinding?.viewModel = viewModel
        otpFragmentBinding?.fragment = this
        return otpFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }

                UserData.UserStatus.USER_OTP_vERIFICATION_FAILED -> {
                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.otp_failed),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()

                }

                UserData.UserStatus.USER_OTP_VERIFICATION_SUCCESS -> {
                    findNavController().navigate(R.id.action_otp_forgetPasswordFragment)
                }


            }
        })
    }

    fun onOTPverified(view: View) {
        viewModel.callOTPVerification()
    }

}