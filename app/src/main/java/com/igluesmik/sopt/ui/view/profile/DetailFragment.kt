package com.igluesmik.sopt.ui.view.profile

import android.os.Bundle
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentDetailBinding
import com.igluesmik.sopt.ui.view.base.BaseFragment
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, ProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail
    override val viewModel = ProfileViewModel()

    private var title : String ?= null
    private var subtitle : String ?= null
    private var resourceId : Int ?= null

    companion object {
        @JvmStatic
        fun newInstance(title: String, subtitle: String, resourceId : Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("subtitle", subtitle)
                    putInt("image", resourceId)
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
            title = it.getString("title")
            subtitle = it.getString("subtitle")
            resourceId = it.getInt("image")
        }
    }

    private fun initView() {
        viewDataBinding.txtTitle.text = title
        viewDataBinding.txtSubtitle.text = subtitle
        viewDataBinding.image.setImageResource(resourceId ?: R.drawable.ic_error)
    }

}