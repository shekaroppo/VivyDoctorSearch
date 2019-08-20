package com.vivy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.vivy.R
import com.vivy.data.model.LoginResponse
import com.vivy.databinding.ActivityLoginBinding
import com.vivy.ui.base.BaseActivity
import com.vivy.ui.home.HomeActivity
import com.vivy.utils.Constants
import com.vivy.utils.DataWrapper

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun layoutId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToModel()

    }

    private fun subscribeToModel() {
        binding.loginViewModel = viewModel
        viewModel.loginMutableLiveData.observe(this, repositoriesLiveDataObserver)
        binding.setLifecycleOwner(this)
    }

    val repositoriesLiveDataObserver = Observer<DataWrapper<LoginResponse>> {
        viewModel.displayLoader(false)
        it?.let {
            if (!it.isError) {
                Constants.ACCESS_TOKEN = it.data!!.accessToken
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                viewModel.setErrorMessage(it.isError, it.errorMessage)
            }
        }
    }


}