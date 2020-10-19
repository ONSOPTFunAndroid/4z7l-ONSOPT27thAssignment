package com.igluesmik.sopt.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igluesmik.sopt.R
import kotlinx.android.synthetic.main.activity_detail_web.*

class DetailWebActivity : AppCompatActivity() {

    private var url : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_web)

        getProfileBundle()
        initView()
    }

    fun getProfileBundle() {
        val bundle = getIntent().getBundleExtra("bundle").also {
            url = it?.getString("url")
        }
    }

    fun initView() {
        if(!url.isNullOrEmpty())
            webView.loadUrl(url!!)
    }
}