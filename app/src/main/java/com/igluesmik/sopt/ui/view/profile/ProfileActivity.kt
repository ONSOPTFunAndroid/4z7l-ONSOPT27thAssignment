package com.igluesmik.sopt.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.SoptApplication
import com.igluesmik.sopt.databinding.ActivityProfileBinding
import com.igluesmik.sopt.model.Profile
import com.igluesmik.sopt.ui.view.adapter.ProfileAdapter
import com.igluesmik.sopt.ui.view.base.BaseActivity
import com.igluesmik.sopt.ui.view.base.BaseViewModel
import com.igluesmik.sopt.ui.view.itemtouch.ItemTouchHelperCallback
import com.igluesmik.sopt.ui.view.login.SignInActivity
import com.igluesmik.sopt.ui.view.login.SignUpActivity
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_profile
    override val viewModel: ProfileViewModel = ProfileViewModel()

    private val profileAdapter = ProfileAdapter(this)
    private val linearLayoutManager = LinearLayoutManager(this)
    private val gridLayoutManager = GridLayoutManager(this,2)

    private lateinit var profileList : MutableList<Profile>

    override fun initStartView() {
        initView()
        initEvent()
    }

    override fun initBeforeBinding() {
        initData()
    }

    override fun initAfterBinding() {

    }

    private fun initView() {
        val itemTouchHelperCallback = ItemTouchHelperCallback(profileAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)

        viewDataBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = profileAdapter
            setHasFixedSize(true)
        }

        itemTouchHelper.attachToRecyclerView(viewDataBinding.recyclerView)
    }

    private fun initEvent() {
        profileAdapter.setOnItemClickListener{
            startDetailActivity(it)
        }
        viewDataBinding.btnLogout.setOnClickListener {
            logout()
        }
        viewDataBinding.btnLinear.setOnClickListener {
            setRecyclerViewLinear()
        }
        viewDataBinding.btnGrid.setOnClickListener {
            setRecyclerViewGrid()
        }
    }

    private fun initData() {
        profileList = mutableListOf(
            Profile("이름","김슬기", R.drawable.ic_smile, false),
            Profile("나이","23", R.drawable.ic_smile, false),
            Profile("파트","안드로이드", R.drawable.ic_smile, false),
            Profile("Github","https://www.github.com/4z7l", R.drawable.ic_smile, true),
            Profile("Blog","https://4z7l.github.io", R.drawable.ic_smile, true)
        )
        profileAdapter.setData(profileList)
    }

    private fun logout() {
        SoptApplication.preferences.setBoolean("auto_login",false)
        startSignInActivity()
        finish()
    }

    private fun startSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java));
    }

    private fun startDetailActivity(profile : Profile) {
        val bundle = Bundle()
        val intent =
        if(profile.isAddress){
            bundle.putString("url",profile.subtitle)
            Intent(this, DetailWebActivity::class.java)
        }
        else {
            bundle.apply {
                putString("title",profile.title)
                putString("subtitle",profile.subtitle)
                putInt("image",profile.resourceId)
            }
            Intent(this, DetailActivity::class.java)
        }
        intent.putExtra("bundle",bundle)

        startActivity(intent)
    }

    private fun setRecyclerViewLinear() {
        viewDataBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = profileAdapter.apply { setLinearItemViewType() }
        }
    }

    private fun setRecyclerViewGrid() {
        viewDataBinding.recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = profileAdapter.apply { setGridItemViewType() }
        }
    }
}
