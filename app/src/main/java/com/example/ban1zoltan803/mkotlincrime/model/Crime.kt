package com.example.ban1zoltan803.mkotlincrime.model

import java.util.*

data class Crime(val id : UUID = UUID.randomUUID(),
                 var title : String = "",
                 var date : Date = Date(),
                 var solved : Boolean = false)
{

}