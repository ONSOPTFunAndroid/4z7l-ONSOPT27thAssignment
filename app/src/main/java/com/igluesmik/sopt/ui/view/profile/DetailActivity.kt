package com.igluesmik.sopt.ui.view.profile

import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivityDetailBinding
import com.igluesmik.sopt.ui.view.base.BaseActivity
import com.igluesmik.sopt.ui.view.base.BaseViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, BaseViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_detail
    override val viewModel: BaseViewModel = BaseViewModel()

    private var title : String ?= null
    private var subtitle : String ?= null
    private var resourceId : Int ?= null

    override fun initStartView() {
        getProfileBundle()
        initView()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun getProfileBundle() {
        intent.getBundleExtra("bundle").also {
            title = it?.getString("title")
            subtitle = it?.getString("subtitle")
            resourceId = it?.getInt("image")
        }
    }

    private fun initView() {
        viewDataBinding.txtTitle.text = title
        viewDataBinding.txtSubtitle.text = subtitle
        viewDataBinding.image.setImageResource(resourceId ?: R.drawable.ic_error)
    }

}