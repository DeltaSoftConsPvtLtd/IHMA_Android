package com.ilaftalkful.ihma.view.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.base.IlafBaseActivity
import com.ilaftalkful.ihma.base.IlafBaseFragment
import com.ilaftalkful.ihma.customcomponents.IlafCommonAlert
import com.ilaftalkful.ihma.databinding.LoginFragmentBinding
import com.ilaftalkful.ihma.model.SignInErrors
import com.ilaftalkful.ihma.model.UserData
import com.ilaftalkful.ihma.utilities.*
import com.ilaftalkful.ihma.view.LoginViewModel
import com.ilaftalkful.ihma.view.SplashActivity
import kotlinx.android.synthetic.main.login_fragment.*
import org.checkerframework.checker.units.qual.Length


class LoginFragment : IlafBaseFragment() {

    val viewModel: LoginViewModel by viewModels()
    var loginFragmentBinding: LoginFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragmentBinding = DataBindingUtil.inflate<LoginFragmentBinding>(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        loginFragmentBinding?.lifecycleOwner = this
        loginFragmentBinding?.viewModel = viewModel
        loginFragmentBinding?.fragment = this
        loginFragmentBinding?.errors = SignInErrors("")
        return loginFragmentBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }
                UserData.UserStatus.LOGIN_SUCCESS -> {
                    findNavController().navigate(R.id.action_show_home_guest)

                }
                UserData.UserStatus.USER_LOGIN_FAILED -> {
                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.login_failed),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()

                }
                UserData.UserStatus.OPERATION_STARTED -> {

//                    showMsg("Singing in...!!")
                }
                UserData.UserStatus.ERROR -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.something_went_wrong),
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



    fun onRegisterClicked(view: View) {
        findNavController().navigate(R.id.action_show_register)
    }




    @RequiresApi(Build.VERSION_CODES.M)
    fun onLoginClicked(view: View, errors: SignInErrors) {
        if (Utility.checkInternetConnection(requireActivity())) {
            (activity as IlafBaseActivity).hideKeyboard()

            if (viewModel.username.value.isNullOrEmpty()) {
                errors.userNameError = "Username cannot be Empty"
            } else if (viewModel.password.value.isNullOrEmpty()) {
                errors.userPasswordError = "Password cannot be Empty"
            } else {
                errors.userNameError = IhmaValidator.isValidUserName(
                    viewModel.username.value!!.trim(),
                    requireActivity()
                )
                errors.userPasswordError = IhmaValidator.isValidUserPassword(
                    viewModel.password.value!!.trim(),
                    requireActivity()
                )
            }

            viewModel.callLogin(errors)
        } else {
            IlafCommonAlert(
                requireActivity(),
                getString(R.string.no_interbnet),
                getString(R.string.ok),
                null,
                null
            ).show()
        }

    }
}








