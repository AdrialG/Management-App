package com.adrialg.managementapp.ui.splash

import android.os.Bundle
import com.adrialg.managementapp.R
import com.adrialg.managementapp.databinding.ActivityMainBinding
import com.adrialg.managementapp.ui.verification.UserVerificationActivity
import com.crocodic.core.base.activity.NoViewModelActivity
import com.crocodic.core.extension.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NoViewModelActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openActivity<UserVerificationActivity> {  }

    }
}