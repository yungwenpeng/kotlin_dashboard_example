package com.example.kotlin_dashboard_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kotlin_dashboard_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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