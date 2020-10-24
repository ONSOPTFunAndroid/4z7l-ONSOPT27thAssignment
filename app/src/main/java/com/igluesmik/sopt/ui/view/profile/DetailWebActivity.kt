package com.igluesmik.sopt.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivityDetailWebBinding
import com.igluesmik.sopt.ui.view.base.BaseActivity
import com.igluesmik.sopt.ui.view.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_detail_web.*

class DetailWebActivity : BaseActivity<ActivityDetailWebBinding, BaseViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_detail_web
    override val viewModel: BaseViewModel = BaseViewModel()

    private var url : String ?= null

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
            url = it?.getString("url")
        }
    }

    private fun initView() {
        if(!url.isNullOrEmpty())
            viewDataBinding.webView.loadUrl(url!!)
    }

}