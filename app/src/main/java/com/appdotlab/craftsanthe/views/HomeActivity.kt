package com.appdotlab.craftsanthe.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appdotlab.craftsanthe.R

class HomeActivity : AppCompatActivity() {
    companion object{
        val TAG:String=HomeActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}