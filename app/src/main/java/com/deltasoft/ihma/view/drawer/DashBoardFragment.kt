package com.deltasoft.ihma.view.drawer

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.deltasoft.ihma.R
import com.deltasoft.ihma.base.IlafBaseFragment
import com.deltasoft.ihma.databinding.DashboardFragmentBinding
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.view.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashBoardFragment : IlafBaseFragment() {

    val viewModel: DashboardViewModel by viewModels()
    lateinit var   dashboardBinding :DashboardFragmentBinding
    lateinit var ilafPreference: IlafSharedPreference
    var userStatusValue:String = ""


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardBinding = DataBindingUtil.inflate<DashboardFragmentBinding>(inflater,R.layout.dashboard_fragment, container, false)
        dashboardBinding.viewModel=viewModel
        dashboardBinding.lifecycleOwner=this
        return dashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ilafPreference = IlafSharedPreference(requireContext())
        val session_token=ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.SESSION_TOKEN)
        Log.d("Session",session_token.toString())



          dashboardBinding.toolbarHome.open_drawer.setOnClickListener {
          dashboardBinding.drawerLayout.open()

          }

        dashboardBinding.navigationView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener({ menuItem ->

            //Clear all fragments previously opened in app
            val direction = DashBoardFragmentDirections.actionDashboardFragmentToLoginFragment()
            findNavController().navigate(direction)
            return@setOnMenuItemClickListener true;


        })


        val  navController:NavController = Navigation.findNavController(view.findViewById(R.id.home_fragment_nav_host))
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.viewProfileFragment ->
                    if(userStatusValue.equals("guestUser")){

                        AlertDialog.Builder(context)
                             .setTitle("Guest User Access Denied ")
                            .setMessage("Please Login to view your Profile.")
                            .setCancelable(false)
                            .setPositiveButton(
                                android.R.string.yes
                            ) { dialog, which -> //do your task

                                findNavController().navigate(R.id.action_dashboard_fragment_to_login_fragment)

                            }
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()
                    }
                R.id.courses_fragment -> {
                    if (userStatusValue.equals("guestUser")) {
                        AlertDialog.Builder(context)
                            .setTitle("Guest User Access Denied ")
                            .setMessage("Please Login to view your Courses.")
                            .setCancelable(false)
                            .setPositiveButton(
                                android.R.string.yes
                            ) { dialog, which -> //do your task

                                findNavController().navigate(R.id.action_dashboard_fragment_to_login_fragment)

                            }
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()
                    }
                    dashboardBinding.toolbarHome.visibility = View.GONE
                }
                else ->
                {
                dashboardBinding.toolbarHome.visibility = View.VISIBLE
                }


            }
        }


       // NavigationUI.setupWithNavController(dashboardBinding.bottomNavigation, navController)
        NavigationUI.setupWithNavController(dashboardBinding.navigationView, navController)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val Value: DashBoardFragmentArgs by navArgs()
        userStatusValue=Value.userStatus
        if(userStatusValue.equals("guestUser")){
            IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_LOGEDIN_USER,false)
        }
    }

}