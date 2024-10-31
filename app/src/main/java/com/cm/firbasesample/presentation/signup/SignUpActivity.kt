package com.cm.firbasesample.presentation.signup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cm.firbasesample.databinding.ActivityMainBinding
import com.cm.firbasesample.databinding.ActivitySignInBinding
import com.cm.firbasesample.databinding.ActivitySignUpBinding
import com.cm.firbasesample.databinding.ActivitySpalshBinding
import com.google.android.material.textfield.TextInputEditText
import com.soda1127.itbookstorecleanarchitecture.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by viewBinding(ActivitySignUpBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding) {

            btnSignUp.setOnClickListener {
                if (checkInfos(etEmail, etBirth, etPhoneNumber, etPassword, etName,etGender)) {
//                    viewModel.signUpWithEmailAndPassword(
//                        User(
//                            email = etEmail.text.toString(),
//                            nickname = etNickname.text.toString(),
//                            phoneNumber = etPhoneNumber.text.toString()
//                        ),
//                        etPassword.text.toString()
//                    )
                }
            }
        }
    }



    private fun checkInfos(
        email: TextInputEditText,
        birth: TextInputEditText,
        phoneNumber: TextInputEditText,
        password: TextInputEditText,
        name: TextInputEditText,
        gender : TextInputEditText
    ): Boolean {
        val checkInfos = when {
            email.text.toString().isEmpty().not() -> false
            birth.text.toString().isEmpty().not() -> false
            phoneNumber.text.toString().isEmpty().not() -> false
            password.text.toString().isEmpty().not() -> false
            name.text.toString().isEmpty().not() -> false
            gender.text.toString().isEmpty().not() -> false
            else -> true
        }
        return checkInfos
    }
}