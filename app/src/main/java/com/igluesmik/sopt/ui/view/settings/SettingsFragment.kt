package com.igluesmik.sopt.ui.view.settings

import android.content.Intent
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentSettingsBinding
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.view.login.SignInActivity
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import com.igluesmik.sopt.util.LoginPreference
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_settings
    override val viewModel by viewModel<UserViewModel>()

    override fun initStartView() {
        initClickEvent()
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initClickEvent(){
        viewDataBinding.btnLogout.setOnClickListener { onSignOutButtonClick() }
    }

    private fun onSignOutButtonClick() {
        LoginPreference.setAutoLogin(false)
        startActivity(Intent(context, SignInActivity::class.java))
        requireActivity().finish()
    }

}