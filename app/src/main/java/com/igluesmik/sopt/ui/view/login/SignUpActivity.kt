package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.databinding.ActivitySignInBinding
import com.igluesmik.sopt.databinding.ActivitySignUpBinding
import com.igluesmik.sopt.ui.view.base.BaseActivity
import com.igluesmik.sopt.ui.view.base.BaseViewModel
import com.igluesmik.sopt.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity<ActivitySignUpBinding, LoginViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_sign_up
    override val viewModel: LoginViewModel = LoginViewModel()

    override fun initStartView() {
        initView()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }


    private fun initView() {
        viewDataBinding.btnRegister.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val id = viewDataBinding.etId.text
        val name = viewDataBinding.etName.text
        val password = viewDataBinding.etPassword.text

        if(id.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() ) {
            Toast.makeText(this,"회원가입 완료!",Toast.LENGTH_SHORT).show()

            val intent = Intent().apply {
                putExtra("id",id.toString())
                putExtra("password",password.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
        else {
            Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
        }
    }
}