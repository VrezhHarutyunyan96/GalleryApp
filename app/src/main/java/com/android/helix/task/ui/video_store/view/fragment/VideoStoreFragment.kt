package com.android.helix.task.ui.video_store.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.helix.task.R
import com.android.helix.task.base.BaseFragment
import com.android.helix.task.databinding.FragmentVideoBinding
import com.android.helix.task.ui.details.viewmodel.DetailsSharedVIewModel
import com.android.helix.task.ui.video_store.view.adapter.VideoStoreAdapter
import com.android.helix.task.utils.NetworkUtil
import org.koin.android.viewmodel.ext.android.sharedViewModel

class VideoStoreFragment : BaseFragment<FragmentVideoBinding>(R.layout.fragment_video) {

    companion object {
        val Tag = "VideoStoreFragment"
        fun getInstance() = VideoStoreFragment().apply {
            arguments = Bundle()
        }
    }

    private val detailsViewModel by sharedViewModel<DetailsSharedVIewModel>()

    private val recyclerAdapter: VideoStoreAdapter by lazy {
        val adapter = VideoStoreAdapter { url ->
            NetworkUtil.getNetworkLiveData().observe(requireActivity(), Observer {
                val isInternetAvailable = it
                isInternetAvailable?.let { isAvailable ->
                }
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                activity?.startActivity(webIntent)
            })
        }
        binding.apply {
            videosRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            videosRecyclerView.adapter = adapter
            videosRecyclerView.setHasFixedSize(true)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(videosRecyclerView)
        }
        return@lazy adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        detailsViewModel.getNews().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.addVideoItems(it)
        })
    }
}