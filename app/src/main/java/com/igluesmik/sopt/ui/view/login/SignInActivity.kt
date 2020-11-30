package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.data.model.domain.User
import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.databinding.ActivitySignInBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.view.MainActivity
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import com.igluesmik.sopt.util.EventObserver
import com.igluesmik.sopt.util.LoginPreference.isAutoLoginSet
import com.igluesmik.sopt.util.LoginPreference.setUserPreference
import com.igluesmik.sopt.util.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, UserViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_sign_in
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeSignInResult()
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
                viewDataBinding.etEmail.setText(data?.getStringExtra("email"))
                viewDataBinding.etPassword.setText(data?.getStringExtra("password"))
            }
        }
    }

    private fun observeSignInResult() {
        viewModel.signInTaskEvent.observe(this, EventObserver{
            if(it is User){
                startMainActivity()
                shortToast("반갑습니다, ${it.userName}님")
            } else if(it is String){
                shortToast(it)
            }
        })
    }

    private fun initClickEvent() {
        viewDataBinding.btnLogin.setOnClickListener { onSignInButtonClick() }
        viewDataBinding.btnRegister.setOnClickListener { onRegisterButtonClick() }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun onSignInButtonClick() {
        val email = viewDataBinding.etEmail.text.toString()
        val password = viewDataBinding.etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            setUserPreference(email, password)
            viewModel.signIn(email,password)
        }
        else {
            shortToast("빈 칸을 채워주세요")
        }
    }

    private fun onRegisterButtonClick() {
        startActivityForResult(Intent(this, SignUpActivity::class.java), SIGN_UP_REQUEST_CODE)
    }

    companion object{
        private const val SIGN_UP_REQUEST_CODE = 116
    }
}