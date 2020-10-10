package com.appdotlab.craftsanthe.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.utils.Constant
import com.appdotlab.craftsanthe.viewmodel.AuthViewModel

class SplashActivity : AppCompatActivity() {
    var delay: Long= 1000
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)

        Constant.methods.ToastL(this,"Loading")
        checkIfUser()
        Handler().postDelayed(
            {
                var i = checkIfUser()
                i.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
            }, delay
        )


    }
    fun checkIfUser(): Intent
    {
        var user = authViewModel.getUser()
        var newUser = authViewModel.isNewUser()
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