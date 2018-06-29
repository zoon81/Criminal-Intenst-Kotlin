package com.example.ban1zoltan803.mkotlincrime

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.ban1zoltan803.mkotlincrime.model.Crime
import com.example.ban1zoltan803.mkotlincrime.model.CrimeLab
import java.util.*

open class CrimeFragment : Fragment(){
    // Starting this fragment, need to pass a crimeID to the newInstance method
    companion object {
        private val ARG_CRIME_ID = "crimeId"
        fun newInstance(crimeId : UUID) : CrimeFragment{
            val arg :Bundle = Bundle()
            arg.putSerializable(ARG_CRIME_ID, crimeId)
            val fragment = CrimeFragment()
            fragment.arguments = arg
            return fragment
        }
    }

    var mCrime = Crime()
    private val DAILOG_DATE = "DialogDate"
    private val REQUEST_DATE : Int = 0

    lateinit var mDateButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_crime, container, false)

        val crimeId = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        mCrime = CrimeLab.get(activity)?.getCrime(crimeId)!!

        var mTitle = v.findViewById(R.id.et_crime_title) as EditText
        mTitle.setText(mCrime.title)
        mTitle.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mCrime.title = p0.toString()
            }
        })

        var mDateButton = v.findViewById(R.id.crime_date) as Button
        mDateButton.text = mCrime.date.toString()
        mDateButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val fm = activity?.supportFragmentManager
                val dialog = DatePickerFragment.newInstance(mCrime.date)
                //dialog.setTargetFragment(CrimeFragment(), REQUEST_DATE)
                dialog.show(fm, DAILOG_DATE)
            }
        })

        return v
    }
//Get the picked date from DatePicker back, store it on DB and update DateButton text
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_DATE){
                val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
                mCrime.date = date
                mDateButton.setText(mCrime.date.toString())
            }
        }
    }
}