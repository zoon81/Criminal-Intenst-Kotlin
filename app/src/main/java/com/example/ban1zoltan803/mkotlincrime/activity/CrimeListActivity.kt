package com.example.ban1zoltan803.mkotlincrime.activity

import android.support.v4.app.Fragment
import com.example.ban1zoltan803.mkotlincrime.CrimeListFragment

class CrimeListActivity : SingleFragmentActivity() {
    override protected fun getFragment(): Fragment {
        return CrimeListFragment.newInstance()
    }
}