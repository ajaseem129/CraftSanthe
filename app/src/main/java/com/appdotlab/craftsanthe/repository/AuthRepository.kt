package com.appdotlab.craftsanthe.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.model.AuthenticatedUser
import com.appdotlab.craftsanthe.model.UserModel
import com.appdotlab.craftsanthe.utils.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

class AuthRepository()
{
    private val auth = FirebaseAuth.getInstance()
    private val firebase = FirebaseFirestore.getInstance()
    private var ref: DocumentReference? = null


    /**
     * Registers the user using Firebase Authentication and returns the status to the ViewModel
     * Authentication method used - Email/Password
     */
    fun register(model: AuthModel,bool: Boolean = true): Single<AuthenticatedUser> {
        return Single.create<AuthenticatedUser> {emitter->
            auth.createUserWithEmailAndPassword(model.email!!, model.password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val uid = user!!.uid
                        emitter.onSuccess(
                            AuthenticatedUser(
                                userID = uid,
                                isNew = true
                            )
                        )
                    }
                    else
                    {
                        emitter.onError(task.exception)
                    }
                }
        }
    }
    fun login(model: AuthModel)
    {
        val authenticatedUser = MutableLiveData<UserModel>()
        auth.signInWithEmailAndPassword(model.email,model.password)
    }

}