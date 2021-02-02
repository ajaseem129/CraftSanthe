package com.appdotlab.craftsanthe.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object Const
{
    const val app_name="Craft Santhe"
    const val SHARED_PREFS = "Craft_Santhe"
    object func{
        fun toastL(context: Context, message:String)
        {
            Toast.makeText(context,message, Toast.LENGTH_LONG).show()
        }
        fun toastS(context: Context, message:String)
        {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }
        fun logD(message: String)
        {
            Log.d(app_name, message)
        }
    }
}