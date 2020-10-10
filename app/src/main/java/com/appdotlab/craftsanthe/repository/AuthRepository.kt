package com.appdotlab.craftsanthe.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.model.UserModel
import com.appdotlab.craftsanthe.utils.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository(private val context: Application)
{
    private var preferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name_short), Context.MODE_PRIVATE)
    private val auth = FirebaseAuth.getInstance()
    private val firebase = FirebaseFirestore.getInstance()
    private var ref: DocumentReference? = null

    fun getUser(): String? {
        return preferences.getString("userID","")
    }
    fun isNewUser():Boolean
    {
        return preferences.getBoolean("newUser",false)
    }
    /**
     * Registers the user using Firebase Authentication and returns the status to the ViewModel
     * Authentication method used - Email/Password
     */
    fun register(model: AuthModel): MutableLiveData<UserModel> {
        val authenticatedUser = MutableLiveData<UserModel>()
        auth.createUserWithEmailAndPassword(model.email!!, model.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val uid = user!!.uid
                    preferences.edit()
                        .putString("userID", uid)
                        .putBoolean("newUser",true)
                        .apply()
                    Constant.methods.ToastL(context, "Your account has been created")
                }
                else
                {
                    Constant.methods.ToastL(context,task.exception?.message.toString())
                }
            }
        return authenticatedUser
    }
    fun login(model: AuthModel)
    {
        val authenticatedUser = MutableLiveData<UserModel>()
        auth.signInWithEmailAndPassword(model.email,model.password)
    }

}