package com.igluesmik.sopt.ui.view.profile

import android.os.Bundle
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentDetailWebBinding
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailWebFragment : BaseFragment<FragmentDetailWebBinding, ProfileViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_detail_web
    override val viewModel : ProfileViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(id : Int) =
            DetailWebFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                }
            }
    }

    override fun initStartView() {

    }

    override fun initDataBinding() {
        viewDataBinding.viewModel = viewModel
        viewModel.getProfileById(arguments?.getInt("id") ?: 1)
    }

    override fun initAfterBinding() {

    }

}