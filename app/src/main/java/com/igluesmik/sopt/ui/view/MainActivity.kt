package com.igluesmik.sopt.ui.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivityMainBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.base.BaseViewModel
import com.igluesmik.sopt.ui.view.profile.ProfileFragment


class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel = BaseViewModel()

    override fun initStartView() {
        startProfileFragment()
        initNavigation()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun startProfileFragment() {
        /*supportFragmentManager.beginTransaction()
            .add(R.id.fragment, ProfileFragment())
            .commit()*/
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


        //viewDataBinding.bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(viewDataBinding.bottomNavigationView, navController)

    }

}