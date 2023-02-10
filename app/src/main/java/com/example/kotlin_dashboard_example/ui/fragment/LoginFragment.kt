package com.example.kotlin_dashboard_example.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_dashboard_example.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private lateinit var btnLogin : Button
    private lateinit var passwordTextInput : TextInputLayout
    private lateinit var passwordEditText : TextInputEditText

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        btnLogin = view.findViewById<View>(R.id.btn_login) as Button
        passwordTextInput = view.findViewById<View>(R.id.password_text_input) as TextInputLayout
        passwordEditText = view.findViewById<View>(R.id.password_edit_text) as TextInputEditText

        // Set an error if the password is less than 8 characters.
        btnLogin.setOnClickListener {
            //Toast.makeText(this.context, "Login successfully", Toast.LENGTH_LONG).show()
            if (!isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = getString(R.string.error_password)
            } else {
                passwordTextInput.error = null
            }
        }

        passwordEditText.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = null
            }
            false
        }

        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun isEmailValid(text: Editable?): Boolean {
        return text != null && text.isNotEmpty()
    }
}