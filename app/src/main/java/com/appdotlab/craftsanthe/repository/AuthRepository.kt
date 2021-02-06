package com.appdotlab.craftsanthe.repository

import android.content.Context
import android.util.Log
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.model.AuthenticatedUser
import com.appdotlab.craftsanthe.model.UserModel
import com.appdotlab.craftsanthe.utils.Const
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firebase = FirebaseFirestore.getInstance()

    companion object {
        private val TAG: String = AuthRepository::class.java.simpleName
        private var instance: AuthRepository? = null

        fun getInstance(context: Context): AuthRepository {
            return if (instance == null) {
                instance = AuthRepository()
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
            auth.createUserWithEmailAndPassword(model.email, model.password)
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
    fun login(authModel: AuthModel): @NonNull Single<UserModel>? {
        return login1(authModel).flatMap {
            getBasicInfo(it.userID!!)
        }
    }
    private fun login1(model: AuthModel): Single<UserModel> {
        return Single.create<UserModel>{emitter->
            auth.signInWithEmailAndPassword(model.email,model.password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful)
                    {
                        emitter.onSuccess(
                            UserModel(
                                auth.currentUser?.email,
                                null,
                                auth.currentUser?.uid,
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

    fun getBasicInfo(userId: String): @NonNull Single<UserModel>? {
        return Single.create<UserModel>{emitter ->
            firebase.collection(Const.USERS).document(userId.toString())
                .get()
                .addOnSuccessListener {
                    if (it.exists())
                        emitter.onSuccess(
                            it.toObject(UserModel::class.java)
                        )
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
    }
    fun setBasicInfo(model:UserModel): @NonNull Single<UserModel> {
        return Single.create<UserModel>{emitter ->
            firebase.collection(Const.USERS).document(model.userID!!).set(model)
                .addOnCompleteListener {
                    emitter.onSuccess(model)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }

        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
    }
}