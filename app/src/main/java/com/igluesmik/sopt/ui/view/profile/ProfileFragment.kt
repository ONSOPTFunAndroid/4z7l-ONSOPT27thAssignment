package com.igluesmik.sopt.ui.view.profile

import android.content.Intent
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.FragmentProfileBinding
import com.igluesmik.sopt.data.model.Profile
import com.igluesmik.sopt.ui.adapter.ProfileAdapter
import com.igluesmik.sopt.ui.base.BaseFragment
import com.igluesmik.sopt.ui.itemtouch.ItemTouchHelperCallback
import com.igluesmik.sopt.ui.view.detail.DetailActivity
import com.igluesmik.sopt.ui.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_profile
    override val viewModel : ProfileViewModel by viewModel()

    private val profileAdapter : ProfileAdapter by lazy { ProfileAdapter(requireContext()) }

    override fun initStartView() {
        initRecyclerView()
        initClickListener()
    }

    override fun initDataBinding() {
        viewDataBinding.fragment = this
        initProfileData()
    }

    override fun initAfterBinding() {
        observeProfileList()
    }

    private fun observeProfileList() {
        viewModel.profileList.observe(viewLifecycleOwner) {
            profileAdapter.setData(it)
            profileAdapter.notifyDataSetChanged()
        }
    }

    private fun initProfileData() {
        viewModel.insert(Profile(1, "이름", "김슬기", R.drawable.ic_smile, false))
        viewModel.insert(Profile(2, "나이", "23", R.drawable.ic_smile, false))
        viewModel.insert(Profile(3, "파트", "안드로이드", R.drawable.ic_smile, false))
        viewModel.insert(
            Profile(
                4,
                "Github",
                "https://www.github.com/4z7l",
                R.drawable.ic_smile,
                true
            )
        )
        viewModel.insert(Profile(5, "Blog", "https://4z7l.github.io", R.drawable.ic_smile, true))
    }

    private fun initRecyclerView() {
        val itemTouchHelperCallback = ItemTouchHelperCallback(profileAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)

        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter
            setHasFixedSize(true)
        }

        itemTouchHelper.attachToRecyclerView(viewDataBinding.recyclerView)
    }

    private fun initClickListener() {
        profileAdapter.setOnItemClickListener{
            startDetailFragment(it)
        }
    }

    private fun startDetailFragment(profile: Profile) {
        /*val fragment = if(profile.isAddress) {
                DetailWebFragment.newInstance(profile.id)
            } else{
                DetailFragment.newInstance(profile.id)
            }

        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
        }
        transaction.commit()*/

        startActivity(Intent(activity, DetailActivity::class.java)
            .putExtra("id",profile.id))
    }

    fun onLinearLayoutButtonClick() {
        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter.apply { setLinearItemViewType() }
        }
    }

    fun onGridLayoutButtonClick() {
        viewDataBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = profileAdapter.apply { setGridItemViewType() }
        }
    }

}