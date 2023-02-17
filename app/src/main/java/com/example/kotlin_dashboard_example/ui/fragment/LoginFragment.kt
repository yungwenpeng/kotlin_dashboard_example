package com.example.kotlin_dashboard_example.ui.fragment

import ApiLoginSuccessResponse
import ApiServiceResponse
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.kotlin_dashboard_example.R
import com.example.kotlin_dashboard_example.api.APIService
import com.example.kotlin_dashboard_example.api.ServerConfig.API_URL
import com.example.kotlin_dashboard_example.api.getLoginUserInfo
import com.example.kotlin_dashboard_example.databinding.LoginFragmentBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    // LoginFragmentBinding : Converting the name of the XML file to Pascal case
    // and adding the word "Binding" to the end
    private var _binding : LoginFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        // inflate(inflater, parent, attachToParent) – Use this in a Fragment or a RecyclerView
        // Adapter (or ViewHolder) where you need to pass the parent ViewGroup to the binding object.
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputTextValid()
    }

    override fun onResume() {
        super.onResume()
        inputTextAction()
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun isUserNameValid(text: Editable?): Boolean {
        return text != null && text.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }

    private fun login() {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            // all the JSON serialization and deserialization is all handled for you.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject().apply {
            put("username", binding.usernameEditText.text)
            put("password", binding.passwordEditText.text)
        }
        //Log.d("LoginFragment", "login: $jsonObject")

        // Create RequestBody ( Not using any converter, like GsonConverter, MoshiConverter
        val requestBody: RequestBody =
            jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response: Response<ResponseBody> = service.login(requestBody)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        // Convert raw JSON to pretty JSON using GSON library
                        val body: String = response.body()!!.string()
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val mBody: ApiLoginSuccessResponse = gson.fromJson(body, ApiLoginSuccessResponse::class.java)
                        //Log.d("Login User token:", mBody.token)
                        //val user = getLoginUserInfo(mBody.token)
                        //Log.d("Login User :", user.sub)
                        val bundle: Bundle = bundleOf(
                            "token" to mBody.token,
                            "username" to getLoginUserInfo(mBody.token).sub
                        )
                        Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                    } else {
                        val errorBody: String = response.errorBody()!!.string()
                        val gson = GsonBuilder().create()
                        val mError: ApiServiceResponse = gson.fromJson(
                            errorBody,
                            ApiServiceResponse::class.java
                        )
                        Log.e("RETROFIT_ERROR (${mError.status})", mError.message)
                        Toast.makeText(context, "ERROR (${mError.status}): ${mError.message}", Toast.LENGTH_LONG).show()
                    }
                } catch (e: HttpException) {
                    Toast.makeText(context, "Exception ${e.message}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun inputTextValid() {
        binding.btnLogin.setOnClickListener {
            if(isUserNameValid(binding.usernameEditText.text)
                && isPasswordValid(binding.passwordEditText.text)) {
                login()
            } else {
                when {
                    isUserNameValid(binding.usernameEditText.text) -> {
                        binding.passwordInputText.error = getString(R.string.error_password)
                    }
                    isPasswordValid(binding.passwordEditText.text) -> {
                        binding.usernameInputText.error = getString(R.string.error_username)
                    }
                    else -> {
                        binding.passwordInputText.error = getString(R.string.error_password)
                        binding.usernameInputText.error = getString(R.string.error_username)
                    }
                }
            }
        }
    }

    // Add an action which will be invoked when the input text is changing.
    private fun inputTextAction() {
        binding.usernameEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null && text.isNotEmpty()) {
                binding.usernameInputText.error = null
            }
        }

        binding.passwordEditText.doOnTextChanged { text, _, _, _ ->
            if ( text != null && text.length >= 8) {
                binding.passwordInputText.error = null
            }
        }
    }
}