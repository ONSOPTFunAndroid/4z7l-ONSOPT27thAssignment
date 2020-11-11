package com.igluesmik.sopt.ui.view.home

import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentOtherBinding
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtherFragment : BaseFragment<FragmentOtherBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_other
    override val viewModel: BaseViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

}