package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igluesmik.sopt.R
import com.igluesmik.sopt.ui.view.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val SIGN_UP_REQUEST_CODE = 116

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==SIGN_UP_REQUEST_CODE){
            if(resultCode== RESULT_OK){
                et_id.setText(data?.getStringExtra("id"))
                et_password.setText(data?.getStringExtra("password"))
            }
        }
    }

    fun initView() {
        btn_register.setOnClickListener {
            startSignUpActivity()
        }
        btn_login.setOnClickListener {
            startProfileActivity()
        }
    }

    fun startSignUpActivity() {
        startActivityForResult(Intent(this, SignUpActivity::class.java), SIGN_UP_REQUEST_CODE);
    }

    fun startProfileActivity() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}