package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.databinding.ActivitySignUpBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initBeforeBinding() {
        viewDataBinding.activity = this
    }

    override fun initAfterBinding() {
        observeSignUpResult()
    }

    private fun observeSignUpResult() {
        viewModel.signUp.observe(this, {
            if(it.success) {
                Toast.makeText(this,"회원가입 완료!",Toast.LENGTH_SHORT).show()

                val intent = Intent().apply {
                    putExtra("id",viewDataBinding.etId.text.toString())
                    putExtra("password",viewDataBinding.etPassword.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }

    fun onSignUpButtonClick() {
        val id = viewDataBinding.etId.text.toString()
        val name = viewDataBinding.etName.text.toString()
        val password = viewDataBinding.etPassword.text.toString()

        if(id.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
            viewModel.signUp(RequestSignUp(email = id, password = password, userName = name))
        }
        else {
            Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
        }
    }
}