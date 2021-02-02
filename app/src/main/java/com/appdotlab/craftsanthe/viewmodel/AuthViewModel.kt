package com.appdotlab.craftsanthe.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.repository.AuthRepository
import com.appdotlab.craftsanthe.utils.Const

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        val Tag=AuthViewModel::class.java.simpleName

    }
    private val context = application
    private var authRepository: AuthRepository = AuthRepository()
    private var preferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name_short), Context.MODE_PRIVATE)

    var registerLiveData:LiveData<String>  = MediatorLiveData()

    fun getUser(): String? {
        return preferences.getString("userID","")
    }
    fun isNewUser():Boolean
    {
        return preferences.getBoolean("newUser",false)
    }
    fun register(model: AuthModel)
    {
        val res= authRepository.register(model,true)
        res.subscribe (
            {
                preferences.edit()
                    .putString("userID", it.userID)
                    .putBoolean("newUser",it.isNew)
                    .apply()
                Const.func.toastL(context, "Your account has been created")
            },
            {
                Const.func.toastL(getApplication(),it.message.toString())
            })
    }
    fun login(model: AuthModel)
    {
        authRepository.login(model)
    }


}