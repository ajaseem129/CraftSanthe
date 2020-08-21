package com.appdotlab.craftsanthe.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import com.appdotlab.craftsanthe.R

class SplashActivity : AppCompatActivity() {
    var delay: Long= 5000
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                toHome()

            }, delay
        )


    }
    fun toHome()
    {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}