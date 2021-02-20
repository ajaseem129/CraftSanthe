package com.appdotlab.craftsanthe.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.utils.Const
import com.appdotlab.craftsanthe.viewmodel.AuthViewModel
import androidx.lifecycle.ViewModelProvider

class SplashActivity : AppCompatActivity() {
    private var delay: Long= 1000
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)
        Const.Func.toastL(this,getString(R.string.loading))
        Handler().postDelayed(
            {
                startActivity(
                    checkIfUser().apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }, delay
        )


    }
    private fun checkIfUser(): Intent
    {
        val user = authViewModel.getUser()
        var newUser:Boolean = false
        user?.let {
            newUser = authViewModel.isNewUser()
        }
        return when {
            user.isNullOrEmpty() ->
                Intent(this, LoginActivity::class.java)
            newUser?:false ->
                Intent(this, EnterBasicInformation::class.java)
            else ->
                Intent(this, HomeActivity::class.java)
        }
    }
}