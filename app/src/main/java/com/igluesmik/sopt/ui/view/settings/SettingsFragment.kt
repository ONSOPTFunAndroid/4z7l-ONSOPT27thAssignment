package com.igluesmik.sopt.ui.view.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentSettingsBinding
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_settings
    override val viewModel by viewModel<LoginViewModel>()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}