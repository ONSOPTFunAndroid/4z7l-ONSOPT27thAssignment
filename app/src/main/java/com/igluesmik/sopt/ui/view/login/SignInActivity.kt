package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.SoptApplication
import com.igluesmik.sopt.databinding.ActivitySignInBinding
import com.igluesmik.sopt.ui.view.MainActivity
import com.igluesmik.sopt.ui.view.base.BaseActivity
import com.igluesmik.sopt.ui.viewmodel.LoginViewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, LoginViewModel>() {

    companion object{
        private const val SIGN_UP_REQUEST_CODE = 116
    }

    override val layoutResourceId: Int = R.layout.activity_sign_in
    override val viewModel: LoginViewModel = LoginViewModel()

    override fun initStartView() {
        initView()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onStart() {
        super.onStart()

        if(SoptApplication.preferences.getBoolean("auto_login", false)){
            startMainActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==SIGN_UP_REQUEST_CODE){
            if(resultCode== RESULT_OK){
                viewDataBinding.etId.setText(data?.getStringExtra("id"))
                viewDataBinding.etPassword.setText(data?.getStringExtra("password"))
            }
        }
    }

    private fun initView() {
        viewDataBinding.btnRegister.setOnClickListener {
            startSignUpActivity()
        }
        viewDataBinding.btnLogin.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val id = viewDataBinding.etId.text
        val password = viewDataBinding.etPassword.text

        if(id.isNotEmpty() && password.isNotEmpty()){
            Toast.makeText(this,"로그인 완료!", Toast.LENGTH_SHORT).show()

            SoptApplication.preferences.setBoolean("auto_login",true)
            SoptApplication.preferences.setString("id",id.toString())
            SoptApplication.preferences.setString("password",password.toString())

            startMainActivity()
        }
        else {
            Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startSignUpActivity() {
        startActivityForResult(Intent(this, SignUpActivity::class.java), SIGN_UP_REQUEST_CODE);
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}