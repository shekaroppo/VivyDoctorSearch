package com.vivy.ui.login

import androidx.lifecycle.MutableLiveData
import com.vivy.data.model.LoginResponse
import com.vivy.data.repository.VivyRepository
import com.vivy.injection.scope.ActivityScope
import com.vivy.ui.base.BaseViewModel
import com.vivy.utils.Constants
import com.vivy.utils.DataWrapper
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(private val vivyRepository: VivyRepository) : BaseViewModel() {
    var loginMutableLiveData = MutableLiveData<DataWrapper<LoginResponse>>()

    fun login() {
        setErrorMessage(false, Constants.EMPTY_MESSAGE)
        displayLoader(true)
        vivyRepository.doLogin(Constants.USER_NAME, Constants.PASSWORD, loginMutableLiveData)
    }

    override fun onCleared() {
        vivyRepository.dispose()
        super.onCleared()
    }
}

