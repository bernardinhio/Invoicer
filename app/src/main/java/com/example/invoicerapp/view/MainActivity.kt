package com.example.invoicerapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.invoicerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * STEPS
 *
 * 1. Use ViewBinding, Jetpack components, MVVM, Navigation
 *
 * 2. I mocked a URL to get Data from so to use Retrofit
 * https://run.mocky.io/v3/cd78c7eb-9ee8-4578-8591-c2a49f90fe38
 *
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}