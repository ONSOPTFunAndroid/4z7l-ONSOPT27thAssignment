package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.SoptApplication
import com.igluesmik.sopt.ui.view.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val SIGN_UP_REQUEST_CODE = 116

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()
    }

    override fun onStart() {
        super.onStart()

        if(SoptApplication.preferences.getBoolean("auto_login", false)){
            startProfileActivity()
        }
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
            if(et_id.text.isNotEmpty() && et_password.text.isNotEmpty()){
                Toast.makeText(this,"로그인 완료!", Toast.LENGTH_SHORT).show()

                SoptApplication.preferences.setBoolean("auto_login",true)
                SoptApplication.preferences.setString("id",et_id.text.toString())
                SoptApplication.preferences.setString("password",et_password.text.toString())

                startProfileActivity()
            }
            else {
                Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
            }
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