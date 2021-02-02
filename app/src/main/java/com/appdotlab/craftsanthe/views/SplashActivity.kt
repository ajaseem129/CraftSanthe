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
    var delay: Long= 1000
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)
        Const.func.toastL(this,"Loading")
        Handler().postDelayed(
            {
                startActivity(
                    checkIfUser().apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }, delay
        )


    }
    fun checkIfUser(): Intent
    {
        val user = authViewModel.getUser()
        val newUser = authViewModel.isNewUser()
        return when {
            user.isNullOrEmpty() ->
                Intent(this, RegisterActivity::class.java)
            newUser ->
                Intent(this, EnterBasicInformation::class.java)
            else ->
                Intent(this, LoginActivity::class.java)
        }
    }
}