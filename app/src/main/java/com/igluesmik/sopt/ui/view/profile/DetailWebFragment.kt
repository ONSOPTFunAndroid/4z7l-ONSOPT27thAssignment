package com.igluesmik.sopt.ui.view.profile

import android.os.Bundle
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentDetailWebBinding
import com.igluesmik.sopt.ui.view.base.BaseFragment
import com.igluesmik.sopt.ui.view.base.BaseViewModel
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel


class DetailWebFragment : BaseFragment<FragmentDetailWebBinding, ProfileViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_detail_web
    override val viewModel: ProfileViewModel = ProfileViewModel()

    private var url : String ?= null

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            DetailWebFragment().apply {
                arguments = Bundle().apply {
                    putString("url", url)
                }
            }
    }

    override fun initStartView() {
        getProfileBundle()
        initView()
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun getProfileBundle() {
        arguments?.let {
            url = it.getString("url")
        }
    }

    private fun initView() {
        if(!url.isNullOrEmpty())
            viewDataBinding.webView.loadUrl(url!!)
    }

}