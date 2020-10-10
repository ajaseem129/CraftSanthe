package com.appdotlab.craftsanthe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private var authRepository: AuthRepository = AuthRepository(application)
    var registerLiveData:LiveData<String>  = MediatorLiveData()

    fun getUser():String? {
        return authRepository.getUser()
    }
    fun isNewUser():Boolean
    {
        return authRepository.isNewUser()
    }
    fun register(model: AuthModel):Boolean
    {
        var res = true
        authRepository.register(model)

        return true
    }
    fun login(model: AuthModel)
    {
        authRepository.login(model)
    }


}