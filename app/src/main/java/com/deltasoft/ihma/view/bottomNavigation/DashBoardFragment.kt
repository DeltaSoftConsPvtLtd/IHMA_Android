package com.deltasoft.ihma.view.bottomNavigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
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


    private var appBarConfiguration: AppBarConfiguration? = null
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null

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

        val radius = resources.getDimension(R.dimen.radius_small)
//        val bottomNavigationViewBackground = dashboardBinding.bottomNavigation.background as MaterialShapeDrawable
//        bottomNavigationViewBackground.shapeAppearanceModel =
//            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
//                .setTopRightCorner(CornerFamily.ROUNDED, radius)
//                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
//                .build()

          dashboardBinding.toolbarHome.open_drawer.setOnClickListener {
          dashboardBinding.drawerLayout.open()

          }

        dashboardBinding.navigationView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener({ menuItem ->

            IlafSharedPreference(requireContext()).setBooleanPrefValue(
                IlafSharedPreference.Constants.IS_LOGEDIN_USER,
                false)

            //Clear all fragments previously opened in app
            val direction = DashBoardFragmentDirections.actionDashboardFragmentToLoginFragment()
            findNavController().navigate(direction)


            return@setOnMenuItemClickListener true;


        })


        val  navController:NavController = Navigation.findNavController(view.findViewById(R.id.home_fragment_nav_host))
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {


                R.id.home_fragment -> {
                    dashboardBinding.toolbarHome.visibility = View.GONE
                }
                else -> {
                    dashboardBinding.toolbarHome.visibility = View.VISIBLE
                }
            }
        }


       // NavigationUI.setupWithNavController(dashboardBinding.bottomNavigation, navController)
        NavigationUI.setupWithNavController(dashboardBinding.navigationView, navController)


    }

}