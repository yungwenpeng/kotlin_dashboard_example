package com.example.kotlin_dashboard_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_dashboard_example.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }
    }
}