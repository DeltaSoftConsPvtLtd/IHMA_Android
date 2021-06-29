package com.deltasoft.ihma.view.drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentNewAboutUsBinding
import com.deltasoft.ihma.viewmodel.AboutUsViewModel


class NewAboutUsFragment : Fragment() {
    val viewModel: AboutUsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aboutUsFragmentBinding = DataBindingUtil.inflate<FragmentNewAboutUsBinding>(
            inflater,
            R.layout.fragment_new_about_us,
            container,
            false
        )
        aboutUsFragmentBinding.lifecycleOwner = this
        aboutUsFragmentBinding.viewModel = viewModel
        aboutUsFragmentBinding.fragment = this
        return aboutUsFragmentBinding.root


    }

    fun onChapterClicked(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_chaptersList)

    }
    fun onFirstDoctor(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_firstDoctor)

    }
    fun onSecondDoctor(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_secondDoctor)

    }
    fun onThirdDoctor(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_thirdDoctor)

    }
    fun onFourthDoctor(view: View) {

        findNavController().navigate(R.id.action_newaboutUs_fragment_to_fourthDoctor)

    }



}
