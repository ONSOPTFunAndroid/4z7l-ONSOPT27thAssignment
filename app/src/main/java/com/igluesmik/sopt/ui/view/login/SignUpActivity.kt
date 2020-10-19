package com.igluesmik.sopt.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.igluesmik.sopt.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
    }

    fun initView() {
        btn_register.setOnClickListener {
            if(et_id.text.isNotEmpty() && et_name.text.isNotEmpty() && et_password.text.isNotEmpty() ) {
                Toast.makeText(this,"회원가입 완료!",Toast.LENGTH_SHORT).show()

                val intent = Intent().apply {
                    putExtra("id",et_id.text.toString())
                    putExtra("password",et_password.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            }
            else {
                Toast.makeText(this,"빈 칸을 채워주세요",Toast.LENGTH_SHORT).show()
            }
        }
    }
}