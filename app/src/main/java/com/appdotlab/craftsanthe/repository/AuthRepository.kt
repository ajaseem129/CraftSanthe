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
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthRepository constructor(context: Context)
{
    private val auth = FirebaseAuth.getInstance()
    private val firebase = FirebaseFirestore.getInstance()
    private var ref: DocumentReference? = null
    companion object {

        private var instance: AuthRepository? = null

        fun getInstance(context: Context): AuthRepository {
            return if (instance == null) {
                instance = AuthRepository(
                    context
                )
                instance!!
            } else {
                instance!!
            }
        }
    }

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
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
    }
    fun login(model: AuthModel): Single<UserModel>? {

        return Single.create<UserModel>{emitter->
            auth.signInWithEmailAndPassword(model.email,model.password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful)
                    {
                        emitter.onSuccess(
                            UserModel(
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                        )
                    }
                    else
                    {
                        emitter.onError(task.exception)
                    }
            }
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
    }
}