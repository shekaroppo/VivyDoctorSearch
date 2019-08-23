package com.takeaway.ui.home

import androidx.navigation.Navigation.findNavController
import com.takeaway.R
import com.takeaway.databinding.ActivityHomeBinding
import com.takeaway.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun layoutId() = R.layout.activity_home

    override fun onSupportNavigateUp() = findNavController(this, R.id.navHostFragment).navigateUp()
}