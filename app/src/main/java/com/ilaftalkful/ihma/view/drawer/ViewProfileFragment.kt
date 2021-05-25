package com.ilaftalkful.ihma.view.drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.bindingAdaptor.CourseAdapter
import com.ilaftalkful.ihma.databinding.ApplyFragmentBinding
import com.ilaftalkful.ihma.databinding.FragmentViewProfileBinding
import com.ilaftalkful.ihma.viewmodel.ApplyViewModel
import com.ilaftalkful.ihma.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.view.*


class ViewProfileFragment : Fragment() {

    val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsFragmentBinding = DataBindingUtil.inflate<FragmentViewProfileBinding>(
            inflater,
            R.layout.fragment_view_profile,
            container,
            false
        )
        productsFragmentBinding.lifecycleOwner = this
        productsFragmentBinding.viewModel = viewModel
        return productsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}