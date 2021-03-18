package com.android.helix.task.ui.home.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.helix.task.AppApplication
import com.android.helix.task.R
import com.android.helix.task.base.BaseFragment
import com.android.helix.task.databinding.FragementHomeBinding
import com.android.helix.task.ui.details.view.DetailsFragment
import com.android.helix.task.ui.details.viewmodel.DetailsSharedVIewModel
import com.android.helix.task.ui.home.view.adapter.HomeNewsFeedAdapter
import com.android.helix.task.ui.home.viewmodel.HomeViewModel
import com.android.helix.task.utils.FragmentAnimationType
import com.android.helix.task.utils.NetworkUtil
import com.android.helix.task.utils.openFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragementHomeBinding>(R.layout.fragement_home) {

    companion object {
        fun getInstance() = HomeFragment().apply {
            arguments = Bundle()
        }
    }

    private val homeViewModel by viewModel<HomeViewModel>()
    private val detailsViewModel by sharedViewModel<DetailsSharedVIewModel>()

    private val recyclerAdapter: HomeNewsFeedAdapter by lazy {
        val adapter = HomeNewsFeedAdapter { newsItem ->
            detailsViewModel.setNews(newsItem)
            openFragment(
                DetailsFragment.getInstance(),
                R.id.main_container,
                DetailsFragment.Tag,
                true,
                isReplace = false,
                animationType = FragmentAnimationType.SLIDE_VERTICAL
            )
        }
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter
        return@lazy adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkUtil.checkNetwork(requireContext())
        loadNewsIfConnectionAvailable()
        initViewModel()
    }

    private fun initViewModel() {
        homeViewModel.getNewsFeedLiveData().observe(viewLifecycleOwner, Observer {
            it.let { }
        })
        homeViewModel.getNewsFeedDBLiveData().observe(viewLifecycleOwner, Observer {

            it?.let {
                recyclerAdapter.setNewsFeed(it)
            } ?: run {
                Toast.makeText(
                    requireContext(),
                    "We don`t have any data for showing",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun loadNewsIfConnectionAvailable() {
        NetworkUtil.getNetworkLiveData().observe(requireActivity(), Observer {
            val isInternetAvailable = it
            isInternetAvailable?.let { isAvailable ->
                AppApplication.isInternetAvailable = isAvailable
                if (isAvailable) {
                    homeViewModel.loadFromApi()
                    homeViewModel.loadFromDB()
                } else {
                    homeViewModel.loadFromDB()
                }
            } ?: run {
                AppApplication.isInternetAvailable = null
                homeViewModel.loadFromDB()
            }
        })
    }
}