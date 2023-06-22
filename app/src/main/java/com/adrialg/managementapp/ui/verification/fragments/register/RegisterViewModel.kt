package com.adrialg.managementapp.ui.verification.fragments.register

import androidx.lifecycle.viewModelScope
import com.adrialg.managementapp.api.ApiService
import com.adrialg.managementapp.base.BaseViewModel
import com.adrialg.managementapp.data.responses.RegisterResponse
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService
): BaseViewModel() {

    private val _registerResponse = MutableSharedFlow<RegisterResponse>()

    fun register(name: String, email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        ApiObserver.run(
            block = {apiService.register(name, email, password, confirmPassword)},
            toast = false,
            listener = object : ApiObserver.ModelResponseListener<RegisterResponse> {

                override suspend fun onLoading(response: RegisterResponse) {
                    _registerResponse.emit(response)
                    _apiResponse.emit(ApiResponse().responseLoading("Signing Up..."))
                }

                override suspend fun onSuccess(response: RegisterResponse) {
                    _registerResponse.emit(response)
                    _apiResponse.emit(ApiResponse().responseSuccess("Account Registered!"))
                }
            }
        )
    }

}