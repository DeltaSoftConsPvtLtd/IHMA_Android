package com.deltasoft.ihma.view.aboutUs

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
import com.deltasoft.ihma.databinding.FragmentAllChaptersBinding
import com.deltasoft.ihma.viewmodel.AllChaptersViewModel
import kotlinx.android.synthetic.main.courses_fragment.view.*


class AllChaptersFragment : Fragment() {
    val viewModel: AllChaptersViewModel by viewModels()
    lateinit var webview: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val allchaptersFragmentBinding = DataBindingUtil.inflate<FragmentAllChaptersBinding>(inflater,R.layout.fragment_all_chapters, container, false)
        allchaptersFragmentBinding.lifecycleOwner=this
        allchaptersFragmentBinding.viewModel=viewModel


        //Preventing Screenrecording and Screenshot

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        return allchaptersFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webview=view.webView
        progressBar=view.webview_prog_bar

        viewModel.showWebView(webview,progressBar)
    }
}