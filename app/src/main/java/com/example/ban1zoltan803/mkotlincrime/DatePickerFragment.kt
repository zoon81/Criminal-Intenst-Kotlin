package com.example.ban1zoltan803.mkotlincrime

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater

import android.widget.DatePicker

import java.util.*

class DatePickerFragment : DialogFragment() {
    companion object {
        val ARG_DATE = "crime_date"
        val EXTRA_DATE ="com.bignerdranch.android.criminalintent.date"

        fun newInstance(date : Date) : DialogFragment{
            val arg = Bundle()
            arg.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = arg
            return fragment
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date_from_crime_fragment = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date_from_crime_fragment

        val v = LayoutInflater.from(activity).inflate(R.layout.dialog_date, null)

        val mDatePicker = v.findViewById<DatePicker>(R.id.dialog_date_dp)
        mDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)

        return AlertDialog.Builder(activity)
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val date = GregorianCalendar(mDatePicker.year, mDatePicker.month, mDatePicker.dayOfMonth).time
                        sendResult(Activity.RESULT_OK, date)
                    }

                })
                .create()
    }
    //Helper for sending the result back to CrimeFragment
    private fun sendResult(resultCode : Int, date : Date){
        if(targetFragment == null ){
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment!!.onActivityResult(targetRequestCode, resultCode, intent)
    }
}