package com.android.helix.task.ui.image_store.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.helix.task.R
import com.android.helix.task.base.BaseFragment
import com.android.helix.task.databinding.FragmentPhotoBinding
import com.android.helix.task.ui.details.view.DetailsFragment
import com.android.helix.task.ui.details.viewmodel.DetailsSharedVIewModel
import com.android.helix.task.ui.home.view.adapter.HomeNewsFeedAdapter
import com.android.helix.task.ui.home.view.fragment.HomeFragment
import com.android.helix.task.ui.image_store.view.adapter.PhotoStoreAdapter
import com.android.helix.task.utils.FragmentAnimationType
import com.android.helix.task.utils.openFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PhotoStoreFragment : BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {

    companion object {
        val Tag = "PhotoStoreFragment"
        fun getInstance() = PhotoStoreFragment().apply {
            arguments = Bundle()
        }
    }

    private val detailsViewModel by sharedViewModel<DetailsSharedVIewModel>()

    private val recyclerAdapter: PhotoStoreAdapter by lazy {
        val adapter = PhotoStoreAdapter()
        binding.apply {
            photosRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            photosRecyclerView.adapter = adapter
            photosRecyclerView.setHasFixedSize(true)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(photosRecyclerView)
        }
        return@lazy adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        detailsViewModel.getNews().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.addPhotoItems(it)
        })
    }

}