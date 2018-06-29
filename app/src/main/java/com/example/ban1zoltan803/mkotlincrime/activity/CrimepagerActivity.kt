package com.example.ban1zoltan803.mkotlincrime.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.ban1zoltan803.mkotlincrime.CrimeFragment
import com.example.ban1zoltan803.mkotlincrime.R
import com.example.ban1zoltan803.mkotlincrime.model.Crime
import com.example.ban1zoltan803.mkotlincrime.model.CrimeLab
import java.util.*


class CrimepagerActivity : AppCompatActivity() {
    private lateinit var  mViewPager : ViewPager
    private lateinit var mCrimes : List<Crime>

    //Defining how we can start this activity
    companion object {
        private val EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

        fun newIntent(packageContext : Context, crimeId : UUID) : Intent {
            val intent = Intent(packageContext, CrimepagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return  intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        mViewPager = findViewById(R.id.activity_crime_pager_vp) as ViewPager
        mCrimes = CrimeLab.get(this)!!.crimes
        val fm = supportFragmentManager
        //mViewPager.adapter = mFragmentStatePagerAdapter(fm, mCrimes) //Same as bellow
        // Anonymous Inner Class implementation
        mViewPager.adapter = (object: FragmentStatePagerAdapter(fm){
            override fun getItem(p0: Int): Fragment {
                val crime = mCrimes[p0]
                return CrimeFragment.newInstance(crime.id)
            }

            override fun getCount() : Int {
               return mCrimes.size
            }

        })
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        for(i : Int in 0..mCrimes.size){
            if(mCrimes[i].id == crimeId){
                mViewPager.currentItem = i
                break
            }
        }
    }
    //Unused in this case
    private class mFragmentStatePagerAdapter(fm : FragmentManager, val mCrimes : List<Crime>) : FragmentStatePagerAdapter(fm){
        override fun getItem(pos: Int): Fragment {
            val crime : Crime = mCrimes.get(pos)
            return CrimeFragment.newInstance(crime.id)
        }

        override fun getCount(): Int {
            return mCrimes.size
        }

    }
}