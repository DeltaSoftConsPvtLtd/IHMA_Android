package com.ilaftalkful.ihma.view.register

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
import com.ilaftalkful.ihma.databinding.RegisterFragmentBinding
import com.ilaftalkful.ihma.model.SignInErrors
import com.ilaftalkful.ihma.model.UserData
import com.ilaftalkful.ihma.model.UserRegistrationErrors
import com.ilaftalkful.ihma.utilities.IhmaValidator
import com.ilaftalkful.ihma.utilities.Utility
import com.ilaftalkful.ihma.view.SplashActivity
import com.ilaftalkful.ihma.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.register_fragment.view.*
import org.checkerframework.checker.units.qual.Length

class RegistrationFragment : IlafBaseFragment() {

    val viewModel: RegisterViewModel by viewModels()

    lateinit var profile_image: ImageView
    lateinit var back_arrow: ImageView
    lateinit var doctor: TextView
    lateinit var student: TextView
    private val pickImage = 100
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val registerFragmentBinding = DataBindingUtil.inflate<RegisterFragmentBinding>(
            inflater,
            R.layout.register_fragment,
            container,
            false
        )
        registerFragmentBinding.lifecycleOwner = this
        registerFragmentBinding?.fragment = this
        registerFragmentBinding.viewModel = viewModel
        registerFragmentBinding?.errors = UserRegistrationErrors("")


//        //Image Uploading
        profile_image = registerFragmentBinding.profileImage
        profile_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)


        }
        back_arrow=  registerFragmentBinding.backarrowId
        back_arrow.setOnClickListener {

            findNavController().navigate(R.id.action_show_login_from_register)

        }
        return registerFragmentBinding.root
    }

    //Taking photo from gallery
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK && requestCode == pickImage) {
                imageUri = data?.data
                profile_image.setImageURI(imageUri)
            }
        }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctor = view.doctor_id as TextView
        student = view.student_id as TextView

        doctor.setOnClickListener(View.OnClickListener {
            doctor.setBackgroundColor(getResources().getColor(R.color.red))
            student.setBackgroundColor(getResources().getColor(R.color.white))


        })
        student.setOnClickListener(View.OnClickListener {
            student.setBackgroundColor(getResources().getColor(R.color.red))
            doctor.setBackgroundColor(getResources().getColor(R.color.white))
            doctor.setText("Doctor")
            doctor.setTextColor(getResources().getColor(R.color.colorAccent))

        })

        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.REGISTRATION_SUCCESS -> {
                    IlafCommonAlert(
                        requireActivity(),
                        getString(R.string.registration_success),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                                findNavController().navigate(R.id.action_show_login_from_register)
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()
                }
                UserData.UserStatus.USER_REGISTRATION_FAILED -> {
                IlafCommonAlert(
                    requireActivity(),getString(R.string.registration_failed),
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

                    var error = it.getError()
                    if (error?.getErrorCode() == 100) {
                        error.errorMessage = getString(R.string.no_interbnet)
                    }

                    IlafCommonAlert(
                        requireActivity(),
                        it.getError()?.getErrorMessage()
                            ?: getString(R.string.something_went_wrong),
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

    @RequiresApi(Build.VERSION_CODES.M)
    fun onRegisterClicked(view: View, error: UserRegistrationErrors) {
        if (Utility.checkInternetConnection(requireActivity())) {
            (activity as IlafBaseActivity).hideKeyboard()

           error.userIdError =
                IhmaValidator.isValidUserId(viewModel.userId.value!!.trim(), requireActivity())
            error.firstnameError =
                IhmaValidator.isValidName(viewModel.firstName.value!!.trim(), requireActivity())
            error.lastnameError =
                IhmaValidator.isValidName(viewModel.lastName.value!!.trim(), requireActivity())
            error.phoneNumberError =
                IhmaValidator.isValidMobile(viewModel.mobile.value!!, requireActivity())
            error.userEmailError =
                IhmaValidator.isValidEmails(viewModel.email.value!!, requireActivity())
            error.registrationNoError =
                IhmaValidator.isValidRegistrationNo(viewModel.email.value!!, requireActivity())
            error.userName =
                IhmaValidator.isValidUserName(viewModel.username.value!!, requireActivity())
            error.passwordError = IhmaValidator.isValidUserPassword(viewModel.password.value,requireActivity())
            error.confirmPasswordError = IhmaValidator.isValidUserPassword(viewModel.confirmpassword.value,requireActivity())

            if(viewModel.password.value.equals(viewModel.confirmpassword.value)){
                error.confirmPasswordError=""
            }else{
                error.confirmPasswordError="Password  & Confirm Password do not match"
            }



            if (IhmaValidator.isNullOrEmpty(error.userIdError)
                &&IhmaValidator.isNullOrEmpty(error.userNameError)
                && IhmaValidator.isNullOrEmpty(error.passwordError)
                && IhmaValidator.isNullOrEmpty(error.phoneNumberError)
                && IhmaValidator.isNullOrEmpty(error.userEmailError)
                && IhmaValidator.isNullOrEmpty(error.registrationNoError))
            {
                viewModel.callRegistration(error)
            }
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


