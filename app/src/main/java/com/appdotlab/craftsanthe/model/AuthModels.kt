package com.appdotlab.craftsanthe.model

data class AuthModel(val email: String, val password: String)
data class AuthenticatedUser(val userID: String,val isNew:Boolean)
data class UserModel(
    val email: String?= null,
    val phone: String?= null,
    val userID: String?= null,
    val username: String?= null,
    val userType: String?= null
)
