package com.appdotlab.craftsanthe.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.model.AuthenticatedUser
import com.appdotlab.craftsanthe.model.UserModel
import com.appdotlab.craftsanthe.repository.AuthRepository
import com.appdotlab.craftsanthe.utils.Const
import com.appdotlab.craftsanthe.views.EnterBasicInformation
import com.appdotlab.craftsanthe.views.HomeActivity
import com.appdotlab.craftsanthe.views.LoginActivity
import com.orhanobut.hawk.Hawk
import io.reactivex.rxjava3.core.Single

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        val Tag=AuthViewModel::class.java.simpleName

    }
    private val context = application
    private var authRepository: AuthRepository = AuthRepository.getInstance(context)
    private var preferences: SharedPreferences = context.getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)

    fun getUser(): String? {
        return preferences.getString(Const.USER_ID,"")
    }
    fun isNewUser():Boolean
    {
        return preferences.getBoolean(Const.IS_NEW,false)
    }
    fun register(model: AuthModel) {
        val res=
            authRepository.register(model,true)
        res.subscribe (
            {
                preferences.edit()
                    .putString(Const.USER_ID, it.userID)
                    .putBoolean(Const.IS_NEW,it.isNew)
                    .apply()
                Hawk.put(Const.USER_ID,it.userID)
                Const.Func.toastL(context, context.getString(R.string.account_has_been_created))
                context.startActivity(
                    Intent(context, EnterBasicInformation::class.java)
                )
            },
            {
                Const.Func.toastL(context,it.message.toString())
            })
    }
    fun login(model: AuthModel)
    {
        authRepository.login(model)?.subscribe(
            {
                preferences.edit {
                    putString(Const.USER_ID,it.userID)
                    Hawk.put(Const.USER_ID,it.userID)
                    Hawk.put(Const.EMAIL,it.email)
                    Hawk.put(Const.USER_TYPE,it.userType)
                    Hawk.put(Const.PHONE,it.phone)
                }
                context.startActivity(
                    Intent(context,HomeActivity::class.java)
                )

            },
            {
                Const.Func.toastL(context,it.message.toString())
            }
        )
    }
    fun enterBasicInfo(model: UserModel)
    {
        authRepository.setBasicInfo(model).subscribe(
            {
                Const.Func.toastL(context, context.getString(R.string.details_updated))
                Hawk.put(Const.USER_ID,it.userID)
                Hawk.put(Const.EMAIL, it.email)
                Hawk.put(Const.USER_NAME, it.username)
                Hawk.put(Const.USER_TYPE,it.userType)
                Hawk.put(Const.PHONE,it.phone)
                preferences.edit().putBoolean(Const.IS_NEW,false).apply()
                context.startActivity(
                    Intent(context,LoginActivity::class.java)
                )
            },
            {
                Const.Func.toastL(context,it.message.toString())
            }
        )
    }


}