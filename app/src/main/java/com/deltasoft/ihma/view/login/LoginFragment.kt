package com.deltasoft.ihma.view.login

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.base.IlafBaseActivity
import com.deltasoft.ihma.base.IlafBaseFragment
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.LoginFragmentBinding
import com.deltasoft.ihma.model.SignInErrors
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.utilities.Utility
import com.deltasoft.ihma.view.LoginViewModel
import com.deltasoft.ihma.view.splash.SplashActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.login_fragment.view.*


class LoginFragment : IlafBaseFragment() {

    val viewModel: LoginViewModel by viewModels()
    var loginFragmentBinding: LoginFragmentBinding? = null
    lateinit var checkBox: CheckBox


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

        initFCM()

        checkBox=view.rememberpwd_checkbox

        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }
                UserData.UserStatus.LOGIN_SUCCESS -> {
                    if(checkBox.isChecked){
                        IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_LOGEDIN_USER,true)
                        //findNavController().navigate(R.id.action_show_home_loggeduser)
                        val action=LoginFragmentDirections.actionShowLoginGuestUser("LoggedUser")
                        findNavController().navigate(action)

                    }else{
                        IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_LOGEDIN_USER,false)
                        //findNavController().navigate(R.id.action_show_home_loggeduser)
                        val action=LoginFragmentDirections.actionShowLoginGuestUser("LoggedUser")
                        findNavController().navigate(action)
                    }



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

    private fun initFCM() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
           // val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, token.toString())
           // Toast.makeText(activity, token, Toast.LENGTH_SHORT).show()
        })
    }


    fun onRegisterClicked(view: View) {
        findNavController().navigate(R.id.action_show_register)
    }

    fun onForgotPassword(view: View) {
        findNavController().navigate(R.id.action_show_login_to_getEmailFragment)

    }
    fun onGuestUserClicked(view: View) {
        val action=LoginFragmentDirections.actionShowLoginGuestUser("guestUser")
        findNavController().navigate(action)

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
//                errors.userNameError = IhmaValidator.isValidUserName(
//                    viewModel.username.value!!.trim(),
//                    requireActivity()
//                )
//                errors.userPasswordError = IhmaValidator.isValidUserPassword(
//                    viewModel.password.value!!.trim(),
//                    requireActivity()
//                )
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








