package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.databinding.ActivitySignInBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.view.MainActivity
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import com.igluesmik.sopt.util.LoginPreference.isAutoLoginSet
import com.igluesmik.sopt.util.LoginPreference.setUserPreference
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, UserViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_sign_in
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initBeforeBinding() {
        viewDataBinding.activity = this
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
                viewDataBinding.etId.setText(data?.getStringExtra("id"))
                viewDataBinding.etPassword.setText(data?.getStringExtra("password"))
            }
        }
    }

    fun onSignInButtonClick() {
        val id = viewDataBinding.etId.text.toString()
        val password = viewDataBinding.etPassword.text.toString()

        if(id.isNotEmpty() && password.isNotEmpty()){
            setUserPreference(id, password)
            viewModel.signIn(RequestSignIn(id,password))
        }
        else {
            Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
        }
    }

    fun onRegisterButtonClick() {
        startActivityForResult(Intent(this, SignUpActivity::class.java), SIGN_UP_REQUEST_CODE);
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun observeSignInResult() {
        viewModel.signIn.observe(this, {
            if(it.success) startMainActivity()
            Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
        })
    }

    companion object{
        private const val SIGN_UP_REQUEST_CODE = 116
    }
}