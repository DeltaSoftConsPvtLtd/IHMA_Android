package com.deltasoft.ihma.view.bottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.JoinFragmentBinding
import com.deltasoft.ihma.viewmodel.JoinViewModel
import kotlinx.android.synthetic.main.courses_fragment.view.*

class AboutUsFragment : Fragment() {


    val viewModel: JoinViewModel by viewModels()
    lateinit var webview: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myLogsFragmentBinding = DataBindingUtil.inflate<JoinFragmentBinding>(inflater,R.layout.join_fragment, container, false)
        myLogsFragmentBinding.lifecycleOwner=this
        myLogsFragmentBinding.viewModel=viewModel


        //Preventing Screenrecording and Screenshot

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        return myLogsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webview=view.webView
        progressBar=view.webview_prog_bar

        viewModel.showWebView(webview,progressBar)
    }

}