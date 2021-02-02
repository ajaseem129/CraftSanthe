package com.appdotlab.craftsanthe.model

data class AuthModel(val email: String, val password: String)
data class AuthenticatedUser(val userID: String,val isNew:Boolean)
data class UserModel(val email: String,val phone: String,val userID: String,val username: String,val userType: String)
