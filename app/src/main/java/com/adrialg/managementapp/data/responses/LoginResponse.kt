package com.adrialg.managementapp.data.responses

import com.adrialg.managementapp.data.User
import com.crocodic.core.api.ModelResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("data")
    @Expose
    val user: User?,
    @SerializedName("token")
    @Expose
    val token: String?
): ModelResponse()