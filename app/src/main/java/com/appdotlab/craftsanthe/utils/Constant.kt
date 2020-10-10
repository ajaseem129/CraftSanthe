package com.appdotlab.craftsanthe.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object Constant
{
    const val app_name_short="CraftSanthe"
    object methods{
        fun ToastL(context: Context, message:String)
        {
            Toast.makeText(context,message, Toast.LENGTH_LONG).show()
        }
        fun ToastS(context: Context, message:String)
        {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }
        fun logd(message: String)
        {
            Log.d(app_name_short, message)
        }
    }
}