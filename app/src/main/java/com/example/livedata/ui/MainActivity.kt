package com.example.livedata.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.livedata.R
import com.example.livedata.fragmentfactory.BlogsFragmentFactory


class MainActivity : AppCompatActivity() {

   //lateinit var  mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory =
            BlogsFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(MainActivity::class.simpleName ,"Inside MainActivity")

        initFragment()

    }

    private fun initFragment() {
        // adding fragment to the mai activity
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_layout, BlogsFragment.newInstance(), "BlogList")
                .commit()
        }
    }

}
