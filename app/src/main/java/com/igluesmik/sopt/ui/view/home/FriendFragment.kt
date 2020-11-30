package com.igluesmik.sopt.ui.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentFriendBinding
import com.igluesmik.sopt.ui.adapter.FriendAdapter
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.viewmodel.FriendViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FriendFragment : BaseFragment<FragmentFriendBinding, FriendViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_friend
    override val viewModel: FriendViewModel by viewModel()

    private val friendAdapter = FriendAdapter()

    override fun initStartView() {
        initRecyclerView()
    }

    override fun initDataBinding() {
        viewModel.getUsers()
    }

    override fun initAfterBinding() {
        observeFriendList()
    }

    private fun initRecyclerView() {
        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = friendAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeFriendList() {
        viewModel.friendList.observe(this, {
            friendAdapter.data = it
        })
    }

}