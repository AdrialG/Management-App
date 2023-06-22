package com.adrialg.managementapp.ui.verification

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.adrialg.managementapp.R
import com.adrialg.managementapp.adapter.PagerAdapter
import com.adrialg.managementapp.databinding.ActivityVerificationBinding
import com.adrialg.managementapp.ui.verification.fragments.login.LoginFragment
import com.adrialg.managementapp.ui.verification.fragments.register.RegisterFragment
import com.crocodic.core.base.activity.NoViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserVerificationActivity : NoViewModelActivity<ActivityVerificationBinding>(R.layout.activity_verification)  {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.verificationPager

        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)

        // Add your fragments to the pagerAdapter
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())
        // Add more fragments if needed

        viewPager.adapter = pagerAdapter
    }
}