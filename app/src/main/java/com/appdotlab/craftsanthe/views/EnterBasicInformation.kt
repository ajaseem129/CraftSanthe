package com.appdotlab.craftsanthe.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.appdotlab.craftsanthe.R
import com.appdotlab.craftsanthe.model.UserModel
import com.appdotlab.craftsanthe.utils.Const
import com.appdotlab.craftsanthe.viewmodel.AuthViewModel
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_enter_basic_information.*

class EnterBasicInformation: AppCompatActivity(),View.OnClickListener {
    lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_basic_information)
    }

    override fun onClick(v: View?) {
        when(v?.id!!)
        {
            R.id.submit->
            {
                authViewModel.enterBasicInfo(
                    UserModel(
                        userID = Hawk.get<String>(Const.USER_ID),
                        email = Hawk.get<String>(Const.EMAIL),
                        username = et_name.text.toString(),
                        userType = if (rb_buyer.isSelected) getString(R.string.buyer) else getString(R.string.seller),
                        phone = et_phone.text.toString()
                    )
                )
            }
        }
    }

}
