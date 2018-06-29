package com.example.ban1zoltan803.mkotlincrime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.ban1zoltan803.mkotlincrime.activity.CrimepagerActivity
import com.example.ban1zoltan803.mkotlincrime.model.Crime
import com.example.ban1zoltan803.mkotlincrime.model.CrimeLab

open class CrimeListFragment : Fragment() {
    companion object {
        fun newInstance() = CrimeListFragment()
    }

    private lateinit var mCrimeRecyclerView: RecyclerView
    private lateinit var mAdapter: CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(R.layout.fragment_crime_list, container, false)
        mCrimeRecyclerView = v.findViewById(R.id.crime_recycle_view) as RecyclerView
        mCrimeRecyclerView.layoutManager = LinearLayoutManager(activity)
        updateUI()
        return v
    }

    private fun updateUI() {
        val mCrimeLab = CrimeLab.get(activity)
        val crimes = mCrimeLab!!.crimes
        if (::mAdapter.isInitialized) {
            mAdapter.notifyDataSetChanged()
        } else {
            mAdapter = CrimeAdapter(activity as Context, crimes)
            mCrimeRecyclerView.adapter = mAdapter
        }
    }
    private class CrimeHolder(val context: Context,itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(p0: View?) {
            val intent : Intent = CrimepagerActivity.newIntent(context, mCrime.id)
            context.startActivity(intent)
        }

        val mTitleTextView = itemView.findViewById<TextView>(R.id.list_item_crime_title_tv)
        val mDateTextView = itemView.findViewById<TextView>(R.id.list_item_crime_date_tv)
        val mSolvedCheckBox = itemView.findViewById<CheckBox>(R.id.list_item_crime_solved_cb)
        private lateinit var mCrime : Crime

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            mCrime = crime
            mTitleTextView.text = mCrime.title
            mDateTextView.text = mCrime.date.toString()
        }

    }

    private class CrimeAdapter(val context: Context, val mCrimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            return CrimeHolder(context,LayoutInflater.from(context).inflate(R.layout.list_item_crime, parent, false))
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            holder.bind(mCrimes[position])
        }

    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
