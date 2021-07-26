package com.deltasoft.ihma.view.drawer

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
import com.deltasoft.ihma.databinding.CoursesFragmentBinding
import com.deltasoft.ihma.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.courses_fragment.view.*

class CoursesFragment : Fragment() {


    val viewModel: HomeViewModel by viewModels()
    var contactFragmentBinding: CoursesFragmentBinding? = null

    lateinit var webview: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         contactFragmentBinding = DataBindingUtil.inflate<CoursesFragmentBinding>(inflater,R.layout.courses_fragment, container, false)
         contactFragmentBinding?.lifecycleOwner=this
         contactFragmentBinding?.viewModel = viewModel


        //Preventing Screenrecording and Screenshot

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        return contactFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

          webview=view.webView
          progressBar=view.webview_prog_bar

          viewModel.showWebView(webview,progressBar)
    }
}