package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivitySignInBinding
import com.igluesmik.sopt.ui.view.MainActivity
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.viewmodel.LoginViewModel
import com.igluesmik.sopt.util.LoginPreference.isAutoLoginSet
import com.igluesmik.sopt.util.LoginPreference.setUserPreference

class SignInActivity : BaseActivity<ActivitySignInBinding, LoginViewModel>() {

    companion object{
        private const val SIGN_UP_REQUEST_CODE = 116
    }

    override val layoutResourceId: Int = R.layout.activity_sign_in
    override val viewModel: LoginViewModel = LoginViewModel()

    override fun initStartView() {

    }

    override fun initBeforeBinding() {
        viewDataBinding.activity = this
    }

    override fun initAfterBinding() {

    }

    override fun onStart() {
        super.onStart()

        if(isAutoLoginSet()){
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

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun onSignInButtonClick() {
        val id = viewDataBinding.etId.text
        val password = viewDataBinding.etPassword.text

        if(id.isNotEmpty() && password.isNotEmpty()){
            setUserPreference(id.toString(), password.toString())
            Toast.makeText(this,"로그인 완료!", Toast.LENGTH_SHORT).show()
            startMainActivity()
        }
        else {
            Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
        }
    }

    fun onRegisterButtonClick() {
        startActivityForResult(Intent(this, SignUpActivity::class.java), SIGN_UP_REQUEST_CODE);
    }

}