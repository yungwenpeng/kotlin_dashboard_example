package com.example.kotlin_dashboard_example.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.kotlin_dashboard_example.R
import com.example.kotlin_dashboard_example.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {
    private var _binding : HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token: String = this.arguments?.getString("token")?:""
        val username: String = this.arguments?.getString("username")?:""
        binding.txtUsername.text = "Hi ${username.substringBefore("@", username).replaceFirstChar { it.uppercase() }}"
    }
}