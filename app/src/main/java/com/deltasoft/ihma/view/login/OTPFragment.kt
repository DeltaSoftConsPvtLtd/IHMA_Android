package com.deltasoft.ihma.view.login

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentOTPBinding
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.view.splash.SplashActivity
import com.deltasoft.ihma.viewmodel.OTPViewModel
import kotlinx.android.synthetic.main.fragment_o_t_p.*


class OTPFragment : Fragment() {


    val viewModel: OTPViewModel by viewModels()
    lateinit var pref: IlafSharedPreference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val otpFragmentBinding = DataBindingUtil.inflate<FragmentOTPBinding>(inflater,R.layout.fragment_o_t_p, container, false)
        otpFragmentBinding.lifecycleOwner=this
        otpFragmentBinding?.viewModel = viewModel
        otpFragmentBinding?.fragment = this

        otpFragmentBinding.otpET1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (otpET1.getText().toString().length == 1) //size as per your requirement
                {
                    otpET2.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        otpFragmentBinding.otpET2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (otpET2.getText().toString().length == 1) //size as per your requirement
                {
                    otpET3.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        otpFragmentBinding.otpET3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (otpET3.getText().toString().length == 1) //size as per your requirement
                {
                    otpET4.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        otpFragmentBinding.otpET4.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (otpET4.getText().toString().length == 1) //size as per your requirement
                {
                    otpET5.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        otpFragmentBinding.otpET5.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (otpET5.getText().toString().length == 1) //size as per your requirement
                {
                    otpET6.requestFocus()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })

        return otpFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = IlafSharedPreference(context)



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
                    val newPassword=pref.getStringPrefValue(IlafSharedPreference.Constants.NEW_PASSWORD)
                    AlertDialog.Builder(context)
                        .setTitle(" New Password")
                        .setMessage("New Password is "+newPassword+"\n Please use it for login")
                        .setCancelable(false)
                        .setPositiveButton(
                            android.R.string.yes
                        ) { dialog, which -> //do your task
                            //Clear all fragments previously opened in app
                            val direction = OTPFragmentDirections.actionOtpLogin()
                            findNavController().navigate(direction)

                        }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()

                }


            }
        })
    }

    fun onOTPverified(view: View) {
        viewModel.callOTPVerification()
    }

}