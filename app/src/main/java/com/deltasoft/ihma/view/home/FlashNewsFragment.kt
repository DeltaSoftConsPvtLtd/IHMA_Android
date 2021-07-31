package com.deltasoft.ihma.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.FlashNewsAdapter
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentFlashNewsBinding
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.view.splash.SplashActivity
import com.deltasoft.ihma.viewmodel.FlasNewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.fragment_flash_news.*


class FlashNewsFragment : Fragment() {
    val viewModel: FlasNewsViewModel by viewModels()
    var adapter: FlashNewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val flashnewsFragmentBinding = DataBindingUtil.inflate<FragmentFlashNewsBinding>(
            inflater,
            R.layout.fragment_flash_news,
            container,
            false
        )
        flashnewsFragmentBinding.lifecycleOwner = this
        flashnewsFragmentBinding.viewModel = viewModel
        adapter = FlashNewsAdapter(viewModel, this)
        flashnewsFragmentBinding.flashnewsRecyclerView.adapter=adapter
        addData()
        observeData()

        return flashnewsFragmentBinding.root
    }





    private fun observeData() {
        viewModel.mutableFlashNewslst.observe(viewLifecycleOwner, Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {
        viewModel.addEventsApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }
                UserData.UserStatus.EMPTY_FLASHNEWS -> {

                    Snackbar.make(flash_news_constraint, R.string.no_flashnews, Snackbar.LENGTH_LONG).show()

                }
                UserData.UserStatus.FLASHNEWS_FAILED -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.failed_flashnews),
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

}

