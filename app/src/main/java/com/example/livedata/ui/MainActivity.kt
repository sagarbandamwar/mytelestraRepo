package com.example.livedata.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.livedata.R


class MainActivity : AppCompatActivity() {

   //lateinit var  mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(MainActivity::class.simpleName ,"Inside MainActivity")

        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.root_layout, BlogsFragment.newInstance(), "BlogList")
                // 5
                .commit()
        }
    }

}
