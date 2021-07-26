package com.deltasoft.ihma.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.base.IlafBaseFragment
import com.deltasoft.ihma.databinding.SplashFragmentBinding
import com.deltasoft.ihma.viewmodel.SplashViewModel

class SplashFragment : IlafBaseFragment() {

    val viewModel: SplashViewModel by viewModels()
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!requireActivity().isFinishing) {

            findNavController().navigate(R.id.action_show_login)

        }
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val splashFragmentBinding = DataBindingUtil.inflate<SplashFragmentBinding>(inflater,R.layout.splash_fragment, container, false)
        splashFragmentBinding.lifecycleOwner=this
        splashFragmentBinding.viewModel=viewModel
        return splashFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }
     override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}