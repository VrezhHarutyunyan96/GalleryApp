package com.android.helix.task.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.helix.task.R
import com.android.helix.task.base.BaseActivity
import com.android.helix.task.databinding.ActivityMainBinding
import com.android.helix.task.ui.home.view.fragment.HomeFragment
import com.android.helix.task.utils.FragmentAnimationType
import com.android.helix.task.utils.openFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val fragment = HomeFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openFragment(
            fragment = fragment,
            addToBackStack = true,
            animationType = FragmentAnimationType.SLIDE_VERTICAL
        )
    }
}