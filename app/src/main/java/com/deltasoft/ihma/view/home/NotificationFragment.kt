package com.deltasoft.ihma.view.home



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.NotificationAdapter
import com.deltasoft.ihma.customcomponents.IlafCommonAlert
import com.deltasoft.ihma.databinding.FragmentNotificationBinding
import com.deltasoft.ihma.model.UserData
import com.deltasoft.ihma.view.splash.SplashActivity
import com.deltasoft.ihma.viewmodel.NotificationViewModel


class NotificationFragment : Fragment() {



    val viewModel: NotificationViewModel by viewModels()
    var adapter:NotificationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationFragmentBinding = DataBindingUtil.inflate<FragmentNotificationBinding>(
            inflater,
            R.layout.fragment_notification,
            container,
            false
        )
        notificationFragmentBinding.lifecycleOwner = this
        notificationFragmentBinding.viewModel = viewModel
        adapter = NotificationAdapter(viewModel, this)
        notificationFragmentBinding.notificationRecycler.adapter=adapter
        addData()
        observeData()

        return notificationFragmentBinding.root
    }





    private fun observeData() {
        viewModel.mutablenotificationlst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {
        viewModel.addNotificationApi()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userLiveData?.observe(viewLifecycleOwner, Observer {
            when (it.getStatus()) {
                UserData.UserStatus.CLICKED -> {
                    (activity as SplashActivity).hideKeyboard()
                }
                UserData.UserStatus.EMPTY_NOTIFICATION -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.no_notifications),
                        getString(R.string.ok),
                        null,
                        object : IlafCommonAlert.IlafDialogListener {
                            override fun onDialogPositiveClick() {
                            }

                            override fun onDialogNegativeClick() {

                            }

                        }).show()

                }
                UserData.UserStatus.NOTIFICATIONS_FAILED -> {

                    IlafCommonAlert(
                        requireActivity(),
                        it.statusMessage ?: getString(R.string.failed_notifications),
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