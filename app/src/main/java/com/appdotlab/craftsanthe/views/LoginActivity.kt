package com.appdotlab.craftsanthe.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.AuthModel
import com.appdotlab.craftsanthe.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_email
import kotlinx.android.synthetic.main.activity_login.editTextTextPassword as et_password

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)
        bt_login.setOnClickListener(this)
        bt_to_register.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.bt_login ->
                login()
            R.id.bt_to_register ->
                startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))

        }
    }
    private fun login()
    {
        authViewModel.login(AuthModel(et_email.text.toString(),et_password.text.toString())) {
            startActivity(
                Intent(this, HomeActivity::class.java)
            )
        }
    }
}