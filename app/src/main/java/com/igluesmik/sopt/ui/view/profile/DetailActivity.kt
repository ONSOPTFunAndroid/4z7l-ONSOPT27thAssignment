package com.igluesmik.sopt.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igluesmik.sopt.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var title : String ?= null
    private var subtitle : String ?= null
    private var resourceId : Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getProfileBundle()
        initView()
    }

    fun getProfileBundle() {
        val bundle = getIntent().getBundleExtra("bundle").also {
            title = it?.getString("title")
            subtitle = it?.getString("subtitle")
            resourceId = it?.getInt("image")
        }
    }

    fun initView() {
        txt_title.text = title
        txt_subtitle.text = subtitle
        image.setImageResource(resourceId ?: R.drawable.ic_error)
    }
}