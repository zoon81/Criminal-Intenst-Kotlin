package com.example.ban1zoltan803.mkotlincrime.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.ban1zoltan803.mkotlincrime.R

abstract class SingleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_fragment_activity)
        var fn = supportFragmentManager as FragmentManager
        var fragment : Fragment? = fn.findFragmentById(R.id.fragment_container)
        if(fragment == null){
            fragment = getFragment()
            fn.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
    protected abstract fun getFragment():Fragment
}