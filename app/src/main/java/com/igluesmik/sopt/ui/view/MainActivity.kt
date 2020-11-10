package com.igluesmik.sopt.ui.view

import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivityMainBinding
import com.igluesmik.sopt.ui.adapter.MainViewPagerAdapter
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.base.BaseViewModel
import com.igluesmik.sopt.ui.view.home.HomeFragment
import com.igluesmik.sopt.ui.view.profile.DetailFragment
import com.igluesmik.sopt.ui.view.profile.ProfileFragment
import com.igluesmik.sopt.ui.view.settings.SettingsFragment
import kotlin.properties.Delegates


class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel = BaseViewModel()

    private lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun initStartView() {
        initBottomNavigation()
        initViewPager()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initBottomNavigation() {
        viewDataBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_home -> index = 0
                R.id.menu_profile -> index = 1
                R.id.menu_settings -> index = 2
            }
            viewDataBinding.viewPager.currentItem = index
            true
        }
    }

    private fun initViewPager() {
        viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragmentList(
            listOf(
                HomeFragment(),
                ProfileFragment(),
                SettingsFragment()
            )
        )
        viewDataBinding.viewPager.adapter = viewPagerAdapter
        viewDataBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                viewDataBinding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }
}