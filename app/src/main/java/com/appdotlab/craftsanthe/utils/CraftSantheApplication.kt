package com.appdotlab.craftsanthe.utils

import android.app.Application
import com.orhanobut.hawk.Hawk

class CraftSantheApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        initHawk()
    }
    private fun initHawk() {
        Hawk.init(this).build()
    }
}