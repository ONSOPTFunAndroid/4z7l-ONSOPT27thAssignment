package com.igluesmik.sopt.ui.view.home

import com.google.android.material.tabs.TabLayout
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentHomeBinding
import com.igluesmik.sopt.ui.adapter.ViewPagerAdapter
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home
    override val viewModel: BaseViewModel by viewModel()

    private lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun initStartView() {
        initViewPager()
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.setFragmentList(listOf(
            ProjectFragment(), OtherFragment()
        ))
        viewDataBinding.viewPager.adapter = viewPagerAdapter

        viewDataBinding.tabLayout.setupWithViewPager(viewDataBinding.viewPager)
        viewDataBinding.tabLayout.apply {
            getTabAt(0)?.text = "Project"
            getTabAt(1)?.text = "Other"
        }
    }
}