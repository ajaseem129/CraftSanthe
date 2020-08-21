package com.appdotlab.craftsanthe.utils

import android.content.Context
import android.widget.Toast

class wrappers
{
    fun ToastL(context: Context,message:String)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    fun ToastS(context: Context,message:String)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}