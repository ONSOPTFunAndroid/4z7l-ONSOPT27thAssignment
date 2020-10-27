package com.igluesmik.sopt.ui.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.SoptApplication
import com.igluesmik.sopt.databinding.FragmentProfileBinding
import com.igluesmik.sopt.model.Profile
import com.igluesmik.sopt.ui.view.adapter.ProfileAdapter
import com.igluesmik.sopt.ui.view.base.BaseFragment
import com.igluesmik.sopt.ui.view.itemtouch.ItemTouchHelperCallback
import com.igluesmik.sopt.ui.view.login.SignInActivity
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_profile
    override val viewModel : ProfileViewModel = ProfileViewModel()

    private val profileAdapter : ProfileAdapter by lazy { ProfileAdapter(requireContext()) }


    private lateinit var profileList : MutableList<Profile>

    override fun initStartView() {
        initView()
        initEvent()
    }

    override fun initDataBinding() {
        initData()
    }

    override fun initAfterBinding() {

    }

    private fun initView() {
        val itemTouchHelperCallback = ItemTouchHelperCallback(profileAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)

        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter
            setHasFixedSize(true)
        }

        itemTouchHelper.attachToRecyclerView(viewDataBinding.recyclerView)
    }

    private fun initEvent() {
        profileAdapter.setOnItemClickListener{
            startDetailFragment(it)
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
            Profile("이름", "김슬기", R.drawable.ic_smile, false),
            Profile("나이", "23", R.drawable.ic_smile, false),
            Profile("파트", "안드로이드", R.drawable.ic_smile, false),
            Profile("Github", "https://www.github.com/4z7l", R.drawable.ic_smile, true),
            Profile("Blog", "https://4z7l.github.io", R.drawable.ic_smile, true)
        )
        profileAdapter.setData(profileList)
    }

    private fun logout() {
        SoptApplication.preferences.setBoolean("auto_login", false)
        startSignInActivity()
        activity?.finish()
    }

    private fun startSignInActivity() {
        startActivity(Intent(context, SignInActivity::class.java));
    }

    private fun startDetailFragment(profile: Profile) {
        val fragment =
            if(profile.isAddress)
                DetailWebFragment.newInstance(profile.subtitle)
            else
                DetailFragment.newInstance(profile.title, profile.subtitle, profile.resourceId)

        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            addToBackStack(null)
        }
        transaction.commit()
    }

    private fun setRecyclerViewLinear() {
        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter.apply { setLinearItemViewType() }
        }
    }

    private fun setRecyclerViewGrid() {
        viewDataBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = profileAdapter.apply { setGridItemViewType() }
        }
    }

}