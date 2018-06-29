package com.example.ban1zoltan803.mkotlincrime.model

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class CrimeLab {

    val crimes: List<Crime>

    fun getCrime(id: UUID): Crime? {
        return crimes.firstOrNull { it.id == id }
    }

    init {
        crimes = ArrayList()
        for (crimeIndex in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$crimeIndex"
            crime.solved = crimeIndex % 2 == 0
            crimes.add(crime)
        }
    }

    companion object {
        var sCrimeLab: CrimeLab? = null

        fun get(context: Context?): CrimeLab? {
            if (sCrimeLab == null) {
                sCrimeLab = CrimeLab()
            }
            return sCrimeLab
        }
    }
}