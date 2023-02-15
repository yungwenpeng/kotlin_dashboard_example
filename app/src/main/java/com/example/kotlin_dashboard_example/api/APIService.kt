package com.example.kotlin_dashboard_example.api

import ApiParseJwtResponse
import android.util.Base64
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.nio.charset.StandardCharsets

interface APIService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("/api/auth/login")
    suspend fun login(@Body request: RequestBody): Response<ResponseBody>
}

// Get the information about the login user
fun getLoginUserInfo(token: String): ApiParseJwtResponse {
    val userData =
        String(Base64.decode(token.split(".")[1], Base64.DEFAULT), StandardCharsets.UTF_8)
    Log.d("getLoginUserName :", userData)
    val gson = GsonBuilder().setPrettyPrinting().create()
    return gson.fromJson(userData, ApiParseJwtResponse::class.java)
}