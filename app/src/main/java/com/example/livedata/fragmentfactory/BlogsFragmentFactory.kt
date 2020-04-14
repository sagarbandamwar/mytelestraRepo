package com.example.livedata.fragmentfactory

import androidx.fragment.app.FragmentFactory
import com.example.livedata.ui.BlogsFragment


// custom fragment hadler
class BlogsFragmentFactory: FragmentFactory() {

    private val TAG: String = "BlogsFragmentFactory"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            BlogsFragment::class.java.name -> {
                BlogsFragment()   // it will return blogs fragment from fragment factory
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }

}