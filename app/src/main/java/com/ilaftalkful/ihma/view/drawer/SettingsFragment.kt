package com.ilaftalkful.ihma.view.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.bindingAdaptor.NotificationAdapter
import com.ilaftalkful.ihma.databinding.FragmentSettingsBinding
import com.ilaftalkful.ihma.databinding.HomeFragmentBinding
import com.ilaftalkful.ihma.viewmodel.HomeViewModel
import com.ilaftalkful.ihma.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.home_fragment.*


class SettingsFragment : Fragment() {

    val viewModel: SettingsViewModel by viewModels()

    lateinit var profile_image: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contactFragmentBinding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,R.layout.fragment_settings, container, false)
        contactFragmentBinding.lifecycleOwner=this

        //Image Uploading
        profile_image = contactFragmentBinding.profileImage
        profile_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)



        }

        return contactFragmentBinding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profile_image.setImageURI(imageUri)
        }
    }
}