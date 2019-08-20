package com.vivy.ui.home

import androidx.navigation.Navigation.findNavController
import com.vivy.R
import com.vivy.databinding.ActivityHomeBinding
import com.vivy.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun layoutId() = R.layout.activity_home

    override fun onSupportNavigateUp() = findNavController(this, R.id.navHostFragment).navigateUp()
}