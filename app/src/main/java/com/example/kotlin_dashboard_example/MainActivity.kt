package com.example.kotlin_dashboard_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_dashboard_example.databinding.ActivityMainBinding
import com.example.kotlin_dashboard_example.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate(inflater) -
        // Use this in an Activity onCreate where there is no parent view to pass to the binding object.
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, LoginFragment())
                .commit()
        }*/
    }
}