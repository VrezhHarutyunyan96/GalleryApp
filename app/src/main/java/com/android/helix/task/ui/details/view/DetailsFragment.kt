package com.android.helix.task.ui.details.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.helix.task.R
import com.android.helix.task.base.BaseFragment
import com.android.helix.task.base.EventObserver
import com.android.helix.task.databinding.FragmentDetailsBinding
import com.android.helix.task.ui.details.viewmodel.DetailsSharedVIewModel
import com.android.helix.task.ui.details.viewmodel.DetailsViewModel
import com.android.helix.task.ui.image_store.view.fragment.PhotoStoreFragment
import com.android.helix.task.ui.video_store.view.fragment.VideoStoreFragment
import com.android.helix.task.utils.FragmentAnimationType
import com.android.helix.task.utils.openFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.sql.Timestamp
import java.util.*

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    companion object {
        val Tag = "DetailsFragment"
        fun getInstance() = DetailsFragment().apply {
            arguments = Bundle()
        }
    }

    private val detailsViewModel by sharedViewModel<DetailsSharedVIewModel>()
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        detailsViewModel.getNews().observe(viewLifecycleOwner, Observer {
            binding.apply {
                newsItem = it
                val timestamp = Timestamp(it.date?.toLong()!!)
                date = Date(timestamp.time).toString()

            }
        })
        viewModel.photoButtonClickEvent.observe(
            viewLifecycleOwner,
            EventObserver(::navigateToPhoto)
        )
        viewModel.videoButtonClickEvent.observe(
            viewLifecycleOwner,
            EventObserver(::navigateToVideo)
        )
    }

    private fun navigateToPhoto(unit: Unit) {
        openFragment(
            PhotoStoreFragment.getInstance(),
            R.id.main_container,
            PhotoStoreFragment.Tag,
            true,
            isReplace = false,
            animationType = FragmentAnimationType.SLIDE_VERTICAL
        )
    }

    private fun navigateToVideo(unit: Unit) {
        openFragment(
            VideoStoreFragment.getInstance(),
            R.id.main_container,
            VideoStoreFragment.Tag,
            true,
            isReplace = false,
            animationType = FragmentAnimationType.SLIDE_VERTICAL
        )
    }
}