package com.example.developernotebook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.developernotebook.R
import com.example.developernotebook.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.root

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(root)
        binding?.let {
            setContentView(it.root)
        }
    }
}