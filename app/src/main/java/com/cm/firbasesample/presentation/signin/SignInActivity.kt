package com.cm.firbasesample.presentation.signin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cm.firbasesample.databinding.ActivityMainBinding
import com.cm.firbasesample.databinding.ActivitySignInBinding
import com.cm.firbasesample.databinding.ActivitySpalshBinding
import com.soda1127.itbookstorecleanarchitecture.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by viewBinding(ActivitySignInBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}