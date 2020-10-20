package com.igluesmik.sopt.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.SoptApplication
import com.igluesmik.sopt.model.Profile
import com.igluesmik.sopt.ui.view.adapter.ProfileAdapter
import com.igluesmik.sopt.ui.view.login.SignInActivity
import com.igluesmik.sopt.ui.view.login.SignUpActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileList : MutableList<Profile>

    private val profileAdapter = ProfileAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initView()
        initEvent()
        initData()
    }

    fun initView() {
        recyclerView.apply {
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(this@ProfileActivity)
        }
    }

    fun initEvent() {
        profileAdapter.onItemClickListener = {
            var bundle = Bundle()
            if(it.isAddress){
                bundle = Bundle().apply {
                    putString("url",it.subtitle)
                }
                val intent = Intent(this, DetailWebActivity::class.java).apply {
                    putExtra("bundle",bundle)
                }
                startActivity(intent)
            }
            else {
                bundle = Bundle().apply {
                    putString("title",it.title)
                    putString("subtitle",it.subtitle)
                    putInt("image",it.resourceId)
                }
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("bundle",bundle)
                }
                startActivity(intent)
            }
        }
        btn_logout.setOnClickListener {
            SoptApplication.preferences.setBoolean("auto_login",false)
            startSignInActivity()
            finish()
        }
    }

    fun initData() {
        profileList = mutableListOf(
            Profile("이름","김슬기", R.drawable.ic_smile, false),
            Profile("나이","23", R.drawable.ic_smile, false),
            Profile("파트","안드로이드", R.drawable.ic_smile, false),
            Profile("Github","https://www.github.com/4z7l", R.drawable.ic_smile, true),
            Profile("Blog","https://4z7l.github.io", R.drawable.ic_smile, true)
        )
        profileAdapter.data = profileList
        profileAdapter.notifyDataSetChanged()
    }

    fun startSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java));
    }
}
