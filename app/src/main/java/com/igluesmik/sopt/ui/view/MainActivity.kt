package com.igluesmik.sopt.ui.view

import android.os.Bundle
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
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun startProfileFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, ProfileFragment())
            .commit()
    }

}