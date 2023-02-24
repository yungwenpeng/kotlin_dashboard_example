package com.example.kotlin_dashboard_example.api

import com.google.gson.annotations.SerializedName

data class ApiServiceResponse (
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("message")
    var message: String = "",
    @SerializedName("errorCode")
    var errorCode: String = "",
    @SerializedName("timestamp")
    var timestamp: String = ""
)

data class ApiLoginSuccessResponse (
    @SerializedName("token")
    val token: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = ""
)

data class ApiParseJwtResponse (
    @SerializedName("sub")
    val sub: String = "",
    @SerializedName("scopes")
    val scopes: List<String> = emptyList(),
    @SerializedName("tenantId")
    val tenantId: String = "",
    @SerializedName("customerId")
    val customerId: String = ""
)

