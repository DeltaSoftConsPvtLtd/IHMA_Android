package com.deltasoft.ihma.view.drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.PrivacypolicyFragmentBinding
import com.deltasoft.ihma.viewmodel.PrivacyPolicyViewModel


class PrivacyPolicyFragment : Fragment() {


    val viewModel: PrivacyPolicyViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val privacyPolicyFragmentBinding = DataBindingUtil.inflate<PrivacypolicyFragmentBinding>(inflater,R.layout.privacypolicy_fragment, container, false)
        privacyPolicyFragmentBinding.lifecycleOwner=this
        privacyPolicyFragmentBinding.viewModel=viewModel


        //Preventing Screenrecording and Screenshot

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        return privacyPolicyFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}