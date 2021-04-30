package com.ilaftalkful.ihma.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.base.IlafBaseFragment
import com.ilaftalkful.ihma.databinding.DashboardFragmentBinding
import com.ilaftalkful.ihma.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.view.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashBoardFragment : IlafBaseFragment() {

    val viewModel: DashboardViewModel by viewModels()
    lateinit var   dashboardBinding :DashboardFragmentBinding

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
        val bottomNavigationViewBackground = dashboardBinding.bottomNavigation.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

          dashboardBinding.toolbarHome.open_drawer.setOnClickListener {
          dashboardBinding.drawerLayout.open()
}


        val  navController:NavController = Navigation.findNavController(view.findViewById(R.id.home_fragment_nav_host))
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

            }
        }
        NavigationUI.setupWithNavController(dashboardBinding.bottomNavigation, navController)
    }

    fun onLoginClicked(view:View){
        findNavController().navigate(R.id.action_dashboard_fragment_to_login_fragment)
    }

}