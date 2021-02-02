package com.appdotlab.craftsanthe.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.utils.Const
import com.appdotlab.craftsanthe.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var AuthModel: AuthModel
    private lateinit var authViewModel: AuthViewModel
    var isValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)
        bt_register.setOnClickListener(this)

    }

    override fun onClick(v: View?)
    {
        when(v!!.id)
        {
            R.id.bt_register -> {
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                val re_password = et_password.text.toString()
                if (password == re_password)
                {
                    AuthModel = AuthModel(email,password)
                    authViewModel.register(AuthModel)
                }
                else
                    Const.func.toastL(this,"Passwords do not match")
            }
        }
    }
}