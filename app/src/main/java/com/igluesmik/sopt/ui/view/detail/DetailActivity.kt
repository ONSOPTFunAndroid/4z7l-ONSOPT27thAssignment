package com.igluesmik.sopt.ui.view.detail

import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivityDetailBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, ProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail
    override val viewModel: ProfileViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initBeforeBinding() {
        viewModel.getProfileById(intent.getIntExtra("id",1))
        viewModel.profileData.observe(this){
            viewDataBinding.profile = it
            if(it.isAddress){
                viewDataBinding.webView.loadUrl(it.subtitle)
                finish()
            }
        }
    }

    override fun initAfterBinding() {

    }
}