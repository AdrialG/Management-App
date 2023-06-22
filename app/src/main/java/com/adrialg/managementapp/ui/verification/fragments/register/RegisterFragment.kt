package com.adrialg.managementapp.ui.verification.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adrialg.managementapp.R
import com.adrialg.managementapp.base.BaseFragment
import com.adrialg.managementapp.databinding.FragmentRegisterBinding
import com.adrialg.managementapp.ui.verification.fragments.login.LoginFragment
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.snacked
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewModel by activityViewModels<RegisterViewModel>()

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> binding?.root?.snacked("Signing Up...")
                            ApiStatus.SUCCESS -> {
                            }
                            ApiStatus.ERROR -> {
                                binding?.root?.snacked("Something Went Wrong")
                            }
                            ApiStatus.EXPIRED -> TODO()
                        }
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(com.adrialg.managementapp.ui.verification.fragments.register.ARG_PARAM1, param1)
                    putString(com.adrialg.managementapp.ui.verification.fragments.register.ARG_PARAM2, param2)
                }
            }
    }

}