package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import android.widget.Toast
import com.igluesmik.sopt.R
import com.igluesmik.sopt.data.model.domain.User
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.databinding.ActivitySignUpBinding
import com.igluesmik.sopt.ui.base.BaseActivity
import com.igluesmik.sopt.ui.viewmodel.UserViewModel
import com.igluesmik.sopt.util.EventObserver
import com.igluesmik.sopt.util.setupToast
import com.igluesmik.sopt.util.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
        initClickEvent()
        setupToast(this, viewModel.toastMessage)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeSignUpResult()
    }

    private fun observeSignUpResult() {
        viewModel.signUpTaskEvent.observe(this, EventObserver{ success ->
            if(success){
                finishActivityResult()
            }
        })
    }

    private fun initClickEvent() {
        viewDataBinding.btnRegister.setOnClickListener {
            onSignUpButtonClick()
        }
    }

    private fun finishActivityResult() {
        val intent = Intent().apply {
            putExtra("email",viewDataBinding.etEmail.text.toString())
            putExtra("password",viewDataBinding.etPassword.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun onSignUpButtonClick() {
        val email = viewDataBinding.etEmail.text.toString()
        val name = viewDataBinding.etName.text.toString()
        val password = viewDataBinding.etPassword.text.toString()

        if(email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
            viewModel.signUp(email, password, name)
        }
        else {
            shortToast("빈 칸을 채워주세요")
        }
    }
}